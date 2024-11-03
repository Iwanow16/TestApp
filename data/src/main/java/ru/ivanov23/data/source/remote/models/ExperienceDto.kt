package ru.ivanov23.data.source.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDto(
    @SerialName("previewText")
    val previewText: String,
    @SerialName("text")
    val text: String
)