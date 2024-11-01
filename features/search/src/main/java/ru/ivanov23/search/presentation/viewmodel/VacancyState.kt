package ru.ivanov23.search.presentation.viewmodel

import ru.ivanov23.search.presentation.adapters.offer.OfferUi
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyUi

sealed class VacancyState {
    data object Loading : VacancyState()
    data class Success(val data: Pair<List<OfferUi>, List<VacancyUi>>) : VacancyState()
    data class Error(val message: String) : VacancyState()
}