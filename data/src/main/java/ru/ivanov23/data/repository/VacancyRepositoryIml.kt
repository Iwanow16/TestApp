package ru.ivanov23.data.repository

import ru.ivanov23.data.local.dao.VacancyDao
import ru.ivanov23.data.mapper.toDomain
import ru.ivanov23.data.remote.api.ApiService
import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Vacancy
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class VacancyRepositoryIml @Inject constructor(
    private val apiService: ApiService,
    private val vacancyDao: VacancyDao,
) : VacancyRepository {

    override suspend fun getData(): Pair<List<Offer>, List<Vacancy>> {
        val (offers, vacancies) = apiService.getData()
        return Pair(
            offers.map { it.toDomain() },
            vacancies.map { it.toDomain() }
        )
    }
}