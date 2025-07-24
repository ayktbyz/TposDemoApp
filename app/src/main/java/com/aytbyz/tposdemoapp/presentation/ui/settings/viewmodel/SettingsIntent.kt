package com.aytbyz.tposdemoapp.presentation.ui.settings.viewmodel

sealed class SettingsIntent {
    data class ChangeLanguage(val languageCode: String) : SettingsIntent()
}