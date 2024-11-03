package ru.ivanov23.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ivanov23.data.source.local.entity.OfferEntity
import ru.ivanov23.data.source.local.entity.VacancyEntity

@Dao
interface VacancyDao {

    @Query("UPDATE vacancies SET isFavorite = NOT isFavorite WHERE id = :vacancyId")
    suspend fun toggleFavourite(vacancyId: String)

    @Query("SELECT COUNT(*) <> 0 FROM vacancies WHERE id IN (:vacancyId)")
    fun isFavoriteFlow(vacancyId: String): Flow<Boolean>

    @Query("SELECT * FROM vacancies")
    fun getVacanciesFlow(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM offers")
    fun getOffersFlow(): Flow<List<OfferEntity>>

    @Query("SELECT * FROM vacancies")
    fun getVacancies(): List<VacancyEntity>

    @Query("SELECT * FROM vacancies WHERE isFavorite")
    fun getFavoriteVacancy(): Flow<List<VacancyEntity>>

    @Query("SELECT COUNT(*) FROM vacancies WHERE isFavorite")
    fun getCountFavoriteVacancy(): Flow<Int>

    @Query("SELECT * FROM offers")
    fun getOffers(): List<OfferEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVacancy(vacancies: List<VacancyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOffers(vacancies: List<OfferEntity>)

    @Query("DELETE FROM vacancies")
    suspend fun clearVacancyRepos()

    @Query("DELETE FROM offers")
    suspend fun clearOfferRepos()
}
