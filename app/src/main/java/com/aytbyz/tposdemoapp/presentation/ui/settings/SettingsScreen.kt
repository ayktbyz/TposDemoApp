package com.aytbyz.tposdemoapp.presentation.ui.settings

import android.app.Activity
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.tposdemoapp.R
import com.aytbyz.tposdemoapp.domain.model.LanguageItem
import com.aytbyz.tposdemoapp.presentation.util.setAppLocale

@Composable
fun SettingsScreen() {
    val activity = LocalActivity.current
    val currentLocale = activity!!.resources.configuration.locales[0].language
    var selectedLanguage by remember { mutableStateOf(currentLocale) }

    val languageOptions = listOf(
        LanguageItem("tr", "Türkçe 🇹🇷"),
        LanguageItem("en", "English 🇺🇸")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.language),
            style = MaterialTheme.typography.titleMedium
        )

        languageOptions.forEach { item ->
            LanguageOption(
                label = item.label,
                isSelected = selectedLanguage == item.code
            ) {
                selectedLanguage = item.code
                setAppLocale(activity, item.code)
            }
        }
    }
}

@Composable
fun LanguageOption(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)

        if (isSelected) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null)
        }
    }
}