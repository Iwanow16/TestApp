package ru.ivanov23.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class OfferEntity(
    @PrimaryKey
    val id: Int,
    val iconId: String?,
    val button: String?,
    val link: String,
    val title: String
)