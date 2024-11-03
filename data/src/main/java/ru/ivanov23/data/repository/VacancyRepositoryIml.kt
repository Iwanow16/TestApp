package ru.ivanov23.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.ivanov23.data.mapper.toDomain
import ru.ivanov23.data.mapper.toEntity
import ru.ivanov23.data.source.local.dao.VacancyDao
import ru.ivanov23.data.source.remote.api.ApiService
import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Vacancy
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryIml @Inject constructor(
    private val apiService: ApiService,
    private val vacancyDao: VacancyDao,
) : VacancyRepository {
    override suspend fun getDataFromRemote() {
        val (offers, vacancies) = apiService.getData()
        vacancyDao.clearOfferRepos()
        vacancyDao.clearVacancyRepos()
        vacancyDao.insertAllOffers(offers.map { it.toEntity() })
        vacancyDao.insertAllVacancy(vacancies.map { it.toEntity() })
    }

    override suspend fun getDataFromLocal(): Pair<List<Offer>, List<Vacancy>> {
        val offers = vacancyDao.getOffers()
        val vacancies = vacancyDao.getVacancies()
        return Pair(
            offers.map { it.toDomain() },
            vacancies.map { it.toDomain() }
        )
    }

    override suspend fun getDataFlow(): Flow<Pair<List<Offer>, List<Vacancy>>> =
        combine(
            vacancyDao.getOffersFlow(),
            vacancyDao.getVacanciesFlow()
        ) { offers, vacancies ->
            Pair(
                offers.map { it.toDomain() },
                vacancies.map { it.toDomain() }
            )
        }

    override suspend fun getFavouriteVacancy(): Flow<List<Vacancy>> =
        vacancyDao.getFavoriteVacancy().map { entity ->
            entity.map { it.toDomain() }
        }

    override suspend fun getCountVacancyFlow(): Flow<Int> =
        vacancyDao.getCountFavoriteVacancy()

    override suspend fun isFavoriteFlow(vacancyId: String): Flow<Boolean> {
        return vacancyDao.isFavoriteFlow(vacancyId)
    }

    override suspend fun toggleFavourite(vacancyId: String) {
        vacancyDao.toggleFavourite(vacancyId)
    }
}