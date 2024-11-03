package ru.ivanov23.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class VacancyEntity(
    @PrimaryKey val id: String,
    val lookingNumber: Int?,
    val title: String,
    val town: String,
    val street: String,
    val house: String,
    val company: String,
    val experiencePreview: String,
    val experienceText: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salaryFull: String,
    val salaryShort: String?,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)
