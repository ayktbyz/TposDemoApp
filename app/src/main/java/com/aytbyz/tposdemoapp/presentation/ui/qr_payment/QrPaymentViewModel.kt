package com.aytbyz.tposdemoapp.presentation.ui.qr_payment

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.domain.model.product.Product
import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.usecase.sale.AddSaleUseCase
import com.aytbyz.tposdemoapp.presentation.ui.qr_payment.model.QrUIState
import com.aytbyz.tposdemoapp.presentation.util.manager.MqttManager
import com.aytbyz.tposdemoapp.presentation.util.extensions.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class QrPaymentViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val addSaleUseCase: AddSaleUseCase
) : ViewModel() {
    private val beep = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

    private val _qrState = MutableStateFlow(QrUIState())
    val qrState = _qrState.asStateFlow()

    private val _showErrorPopup = MutableStateFlow(false)
    val showErrorPopup = _showErrorPopup.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _mqttConnected = MutableStateFlow(false)
    val mqttConnected = _mqttConnected.asStateFlow()

    private var scannedProduct: Product? = null

    init {
        beep.startTone(ToneGenerator.TONE_PROP_BEEP, 250)

        MqttManager.connect(context) { success ->
            _mqttConnected.value = success

            if (success && scannedProduct != null) {
                sendMqttMessage(scannedProduct!!)
            }
        }
    }

    fun onQrScanned(data: String) {
        if (_qrState.value.scannedProduct != null) return

        if (data.isBlank()) {
            _errorMessage.value = context.getString(R.string.error_data_blank)
            _showErrorPopup.value = true
            return
        }

        try {
            val product = parseProductData(data)
            scannedProduct = product
            startCountdown()
            _qrState.update { it.copy(scannedProduct = product) }

            if (mqttConnected.value) {
                sendMqttMessage(product)
            }

            beep.startTone(ToneGenerator.TONE_PROP_BEEP, 250)
        } catch (e: Exception) {
            _errorMessage.value = context.getString(R.string.error_data_invalid)
            _showErrorPopup.value = true
        }
    }

    private fun sendMqttMessage(product: Product) {
        val payload = mapOf(
            "deviceId" to "TPOS1234",
            "paymentType" to "QR",
            "productId" to product.id,
            "price" to product.price,
            "timestamp" to System.currentTimeMillis() / 1000
        ).toJson()

        MqttManager.publish("payment/qr", payload, qos = 1) { success ->
            if (success) {
                beep.startTone(ToneGenerator.TONE_PROP_BEEP, 250)

                viewModelScope.launch {
                    val sale = Sale(
                        productId = product.id.toIntOrNull() ?: 0,
                        productName = product.name,
                        cardUid = "",
                        paymentMethod = "QR",
                        price = product.price,
                        timestamp = System.currentTimeMillis()
                    )

                    addSaleUseCase(sale)
                }

                _qrState.update {
                    it.copy(
                        mqttPublishSuccess = true,
                        countdownVisible = false,
                        remainingTime = 0
                    )
                }
            }
        }
    }

    private fun startCountdown() {
        viewModelScope.launch {
            for (i in 30 downTo 0) {
                _qrState.update { it.copy(countdownVisible = true, remainingTime = i) }
                delay(1000)

                if (_qrState.value.mqttPublishSuccess) return@launch
            }

            if (!_qrState.value.mqttPublishSuccess) {
                _errorMessage.value = context.getString(R.string.error_timeout)
                _showErrorPopup.value = true
            }
        }
    }

    // Dummy QR Data:
    // 2:Payfone N750:849.99:imageLink
    private fun parseProductData(data: String): Product {
        val parts = data.split(":")
        require(parts.size >= 4)

        val id = parts[0]
        val name = parts[1]
        val price = parts[2].toDoubleOrNull() ?: 0.0
        val imageUrl = parts.subList(3, parts.size).joinToString(":")

        return Product(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl
        )
    }

    override fun onCleared() {
        super.onCleared()
        beep.release()
    }

    fun clearErrorPopup() {
        _errorMessage.value = ""
        _showErrorPopup.value = false
    }
}