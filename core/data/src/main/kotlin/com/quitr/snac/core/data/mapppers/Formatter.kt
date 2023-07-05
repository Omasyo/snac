package com.quitr.snac.core.data.mapppers

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate

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

internal fun String.getYear() : String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("yyyy").format(date)
}

internal fun String.formatDate() : String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(this)
    return SimpleDateFormat("dd MMM yyyy").format(date)
}