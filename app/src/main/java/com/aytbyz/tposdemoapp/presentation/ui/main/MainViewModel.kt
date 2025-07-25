package com.aytbyz.tposdemoapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.tposdemoapp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _cardUid = MutableStateFlow<String?>(null)
    val cardUid: StateFlow<String?> = _cardUid

    fun onCardScanned(uid: String) {
        _cardUid.value = uid
    }

    init {
        viewModelScope.launch {
            productRepository.initializeProductsIfNeeded()
        }
    }
}