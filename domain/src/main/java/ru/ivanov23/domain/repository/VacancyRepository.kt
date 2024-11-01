package ru.ivanov23.domain.repository

import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Vacancy

interface VacancyRepository {
    suspend fun getData(): Pair<List<Offer>, List<Vacancy>>
}