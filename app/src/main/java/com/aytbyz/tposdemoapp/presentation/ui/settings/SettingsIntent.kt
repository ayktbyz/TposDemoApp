package com.aytbyz.tposdemoapp.presentation.ui.settings

sealed class SettingsIntent {
    data class ChangeLanguage(val languageCode: String) : SettingsIntent()
}