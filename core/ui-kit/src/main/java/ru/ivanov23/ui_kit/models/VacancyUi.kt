package ru.ivanov23.ui_kit.models

data class VacancyUi(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val town: String,
    val street: String,
    val house: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salaryFull: String,
    val shortShort: String?,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)