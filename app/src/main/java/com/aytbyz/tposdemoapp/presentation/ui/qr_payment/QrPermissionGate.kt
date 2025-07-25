package com.aytbyz.tposdemoapp.presentation.ui.qr_payment

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.aytbyz.tposdemoapp.presentation.util.CameraPermissionUtil

@Composable
fun QrPermissionGate(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit = {}
) {
    val context = LocalContext.current
    var askedBefore by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) onPermissionGranted() else onPermissionDenied()
    }

    LaunchedEffect(Unit) {
        if (CameraPermissionUtil.isCameraPermissionGranted(context)) {
            onPermissionGranted()
        } else if (!askedBefore) {
            launcher.launch(Manifest.permission.CAMERA)
            askedBefore = true
        } else {
            onPermissionDenied()
        }
    }
}