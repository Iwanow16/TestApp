package ru.ivanov23.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Vacancy

interface VacancyRepository {
    suspend fun getDataFromRemote()
    suspend fun getDataFromLocal(): Pair<List<Offer>, List<Vacancy>>
    suspend fun getDataFlow(): Flow<Pair<List<Offer>, List<Vacancy>>>
    suspend fun getFavouriteVacancy(): Flow<List<Vacancy>>
    suspend fun getCountVacancyFlow(): Flow<Int>
    suspend fun isFavoriteFlow(vacancyId: String): Flow<Boolean>
    suspend fun toggleFavourite(vacancyId: String)
}