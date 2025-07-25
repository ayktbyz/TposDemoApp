package com.aytbyz.tposdemoapp.presentation.ui.nfc_payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.tposdemoapp.domain.usecase.sale.CreateSaleWithRandomProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NfcPaymentViewModel @Inject constructor(
    private val createSaleUseCase: CreateSaleWithRandomProductUseCase
) : ViewModel() {

    private val _cardUid = MutableStateFlow<String?>(null)
    val cardUid: StateFlow<String?> = _cardUid

    private val _saleCreateSuccess = MutableStateFlow<Boolean>(false)
    val saleCreateSuccess: StateFlow<Boolean> = _saleCreateSuccess

    fun onCardScanned(uid: String) {
        _cardUid.value = uid

        viewModelScope.launch {
            try {
                createSaleUseCase(uid)
                _saleCreateSuccess.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _saleCreateSuccess.value = false
            }
        }
    }
}