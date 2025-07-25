package com.aytbyz.tposdemoapp.presentation.ui.sales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.tposdemoapp.domain.model.sale.Sale
import com.aytbyz.tposdemoapp.domain.usecase.sale.DeleteSaleUseCase
import com.aytbyz.tposdemoapp.domain.usecase.sale.GetSalesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class SalesListViewModel @Inject constructor(
    private val getSalesUseCase: GetSalesUseCase,
    private val deleteSaleUseCase: DeleteSaleUseCase
) : ViewModel() {

    private val _sales = MutableStateFlow<List<Sale>>(emptyList())
    val sales: StateFlow<List<Sale>> = _sales

    private val _eventChannel = Channel<SalesListEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    init {
        loadSales()
    }

    private fun loadSales() {
        viewModelScope.launch {
            _sales.value = getSalesUseCase()
        }
    }

    fun deleteSale(id: Int) {
        viewModelScope.launch {
            deleteSaleUseCase(id)
            loadSales()

            _eventChannel.send(SalesListEvent.ShowSaleDeletedToast)
        }
    }

    sealed class SalesListEvent {
        data object ShowSaleDeletedToast : SalesListEvent()
    }
}