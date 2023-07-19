package com.keetr.snac.core.data.mapppers

import android.util.Log
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date

const val TAG = "Formatter"

internal fun Double.formatTo1dp() = "%.1f".format(this)

internal fun Int.formatToUsd(): String = when (this) {
    0 -> ""
    in 1..999_999 -> DecimalFormat("\$ ###,###").format(this)
    in 1_000_000..999_999_999 -> {
        val million = this / 1_000_000f
        DecimalFormat("$ #.# Million").format(million)
    }

    else -> {
        val billion = this / 1_000_000_000f
        DecimalFormat("$ #.# Billion").format(billion)
    }
}

internal fun String.getYear(): String = try {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    SimpleDateFormat("yyyy").format(date)
} catch (exception: Exception) {
    Log.d(TAG, "getYear: Error parsing $this - $exception")
    throw exception
}


internal fun String.formatDate(): String {
    if(isBlank()) return ""
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("dd MMM yyyy").format(date)
}