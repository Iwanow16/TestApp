package ru.ivanov23.domain.usecases

import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Vacancy
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class GetVacancyUseCase @Inject constructor(
    private val repository: VacancyRepository
) {
    suspend operator fun invoke(): Pair<List<Offer>, List<Vacancy>> {
        return repository.getData()
    }
}