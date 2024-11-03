package ru.ivanov23.data.source.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    @SerialName("address")
    val address: AddressDto,
    @SerialName("appliedNumber")
    val appliedNumber: Int? = null,
    @SerialName("company")
    val company: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("experience")
    val experience: ExperienceDto,
    @SerialName("id")
    val id: String,
    @SerialName("isFavorite")
    val isFavorite: Boolean,
    @SerialName("lookingNumber")
    val lookingNumber: Int? = null,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("questions")
    val questions: List<String>,
    @SerialName("responsibilities")
    val responsibilities: String,
    @SerialName("salary")
    val salary: SalaryDto,
    @SerialName("schedules")
    val schedules: List<String>,
    @SerialName("title")
    val title: String
)