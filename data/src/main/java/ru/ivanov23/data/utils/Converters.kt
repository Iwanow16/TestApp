package ru.ivanov23.data.utils

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let { Json.decodeFromString(it) }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.let { Json.encodeToString(it) }
    }
}

