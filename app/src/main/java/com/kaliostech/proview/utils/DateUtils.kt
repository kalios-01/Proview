package com.kaliostech.proview.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatDate(date: Date): String {
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        
        return "${dateFormatter.format(date)}  ${timeFormatter.format(date)}"
    }
} 