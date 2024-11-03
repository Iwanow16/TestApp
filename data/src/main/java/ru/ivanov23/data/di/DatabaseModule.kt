package ru.ivanov23.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.ivanov23.data.source.local.VacancyDatabase
import ru.ivanov23.data.source.local.dao.VacancyDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): VacancyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            VacancyDatabase::class.java,
            "favorite_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: VacancyDatabase): VacancyDao {
        return database.vacancyDao()
    }
}