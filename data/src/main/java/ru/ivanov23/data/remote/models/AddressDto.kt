package ru.ivanov23.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    @SerialName("house")
    val house: String,
    @SerialName("street")
    val street: String,
    @SerialName("town")
    val town: String
)