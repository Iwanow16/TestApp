package ru.ivanov23.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ivanov23.data.local.dao.VacancyDao
import ru.ivanov23.data.local.entity.VacancyEntity

@Database(entities = [VacancyEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}