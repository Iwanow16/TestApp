package ru.ivanov23.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ivanov23.data.local.entity.VacancyEntity

@Dao
interface VacancyDao {

    @Query("SELECT * FROM vacancy")
    fun getUsers(): Flow<List<VacancyEntity>>
}