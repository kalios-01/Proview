package com.kaliostech.proview.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

object PermissionUtils {
    
    fun hasStoragePermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            true // For Android 10 and below, regular permissions are handled differently
        }
    }
    
    fun getManageStorageIntent(context: Context): Intent {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        return intent
    }
}

@Composable
fun RequestStoragePermission(
    onPermissionResult: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(PermissionUtils.hasStoragePermission(context)) }
    
    // Check permission on composition
    LaunchedEffect(key1 = Unit) {
        hasPermission = PermissionUtils.hasStoragePermission(context)
        onPermissionResult(hasPermission)
    }
} 