package com.aytbyz.tposdemoapp.domain.model.language

data class Language(val code: String, val label: String) {
    companion object {
        val TR = Language("tr", "Türkçe 🇹🇷")
        val EN = Language("en", "English 🇺🇸")

        val allLanguages = listOf(TR, EN)
    }
}