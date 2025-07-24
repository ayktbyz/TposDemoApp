package com.aytbyz.tposdemoapp.presentation.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aytbyz.tposdemoapp.presentation.util.setAppLocale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {

    private val _selectedLanguage = MutableStateFlow("en")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    fun setLanguage(language: String) {
        _selectedLanguage.value = language
    }

    fun initializeCurrentLocale(locale: String) {
        _selectedLanguage.value = locale
    }

    fun onIntent(intent: SettingsIntent, context: Context) {
        when (intent) {
            is SettingsIntent.ChangeLanguage -> {
                _selectedLanguage.value = intent.languageCode
                setAppLocale(context, intent.languageCode)
            }
        }
    }
}