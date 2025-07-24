package com.aytbyz.tposdemoapp.presentation.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.*

fun setAppLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = Configuration()
    config.setLocale(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    if (context is Activity) {
        context.finish()
        context.startActivity(context.intent)
    }
}