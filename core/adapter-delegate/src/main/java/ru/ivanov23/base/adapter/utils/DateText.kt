package ru.ivanov23.base.adapter.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.convertDateToText(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
    val date = inputFormat.parse(dateString)
    return String.format(
        getString(ru.ivanov23.ui_kit.R.string.published_date),
        outputFormat.format(date!!)
    )
}