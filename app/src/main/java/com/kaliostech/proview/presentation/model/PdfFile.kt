package com.kaliostech.proview.presentation.model

import android.net.Uri
import java.util.Date

data class PdfFile(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val lastModified: Date,
    val path: String
) 