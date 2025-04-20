package com.kaliostech.proview.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PdfUtils {

    suspend fun generateThumbnail(context: Context, uri: Uri, width: Int, height: Int): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val pfd = context.contentResolver.openFileDescriptor(uri, "r")
                pfd?.use { fileDescriptor ->
                    val renderer = PdfRenderer(fileDescriptor)
                    if (renderer.pageCount > 0) {
                        val page = renderer.openPage(0)
                        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                        page.close()
                        renderer.close()
                        return@withContext bitmap
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            null
        }
    }
}

@Composable
fun rememberPdfThumbnail(
    uri: Uri,
    width: Int = 300,
    height: Int = 300
): Bitmap? {
    val context = LocalContext.current
    val thumbnail = remember { mutableStateOf<Bitmap?>(null) }
    
    LaunchedEffect(uri) {
        thumbnail.value = PdfUtils.generateThumbnail(context, uri, width, height)
    }
    
    return thumbnail.value
} 