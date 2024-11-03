package ru.ivanov23.data.source.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDto(
    @SerialName("button")
    val button: ButtonDto? = null,
    @SerialName("id")
    val iconId: String? = null,
    @SerialName("link")
    val link: String,
    @SerialName("title")
    val title: String
)