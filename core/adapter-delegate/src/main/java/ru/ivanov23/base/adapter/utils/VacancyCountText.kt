package ru.ivanov23.base.adapter.utils

import android.content.Context

fun Context.getVacancyCountText(count: Int): String {
    return when {
        count % 100 in 11..19 -> getString(ru.ivanov23.ui_kit.R.string.vacancy_form_many)
        count % 10 == 1 -> getString(ru.ivanov23.ui_kit.R.string.vacancy_form_one)
        count % 10 in 2..4 -> getString(ru.ivanov23.ui_kit.R.string.vacancy_form_few)
        else -> getString(ru.ivanov23.ui_kit.R.string.vacancy_form_many)
    }
}