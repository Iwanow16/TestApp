package ru.ivanov23.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.ivanov23.data.source.local.dao.VacancyDao
import ru.ivanov23.data.source.local.entity.OfferEntity
import ru.ivanov23.data.source.local.entity.VacancyEntity
import ru.ivanov23.data.utils.Converters

@Database(
    entities = [VacancyEntity::class, OfferEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VacancyDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}