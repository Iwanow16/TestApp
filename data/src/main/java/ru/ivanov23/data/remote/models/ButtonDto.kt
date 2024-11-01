package ru.ivanov23.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ButtonDto(
    @SerialName("text")
    val text: String
)