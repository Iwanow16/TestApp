package ru.ivanov23.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.ivanov23.data.local.FavoriteDatabase
import ru.ivanov23.data.local.dao.VacancyDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: FavoriteDatabase): VacancyDao {
        return database.vacancyDao()
    }
}