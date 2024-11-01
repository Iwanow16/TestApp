package ru.ivanov23.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey val id: Int
)
