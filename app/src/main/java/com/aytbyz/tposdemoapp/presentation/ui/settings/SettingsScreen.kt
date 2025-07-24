package com.aytbyz.tposdemoapp.presentation.ui.settings

import androidx.activity.compose.LocalActivity
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
import com.aytbyz.tposdemoapp.domain.model.language.Language

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val activity = LocalActivity.current
    val localeFromContext =
        activity?.resources?.configuration?.locales?.get(0)?.language ?: Language.TR.code
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initializeCurrentLocale(localeFromContext)
    }

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

        Language.allLanguages.forEach { language ->
            LanguageOption(
                label = language.label,
                isSelected = selectedLanguage == language.code
            ) {
                activity?.let {
                    viewModel.onIntent(SettingsIntent.ChangeLanguage(language.code), it)
                }
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