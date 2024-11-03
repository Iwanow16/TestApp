package ru.ivanov23.data.source.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobDataDto(
    @SerialName("offers")
    val offers: List<OfferDto>,
    @SerialName("vacancies")
    val vacancies: List<VacancyDto>
)