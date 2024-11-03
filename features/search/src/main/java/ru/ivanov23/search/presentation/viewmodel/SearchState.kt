package ru.ivanov23.search.presentation.viewmodel

import ru.ivanov23.ui_kit.models.OfferUi
import ru.ivanov23.ui_kit.models.VacancyUi

sealed class SearchState {
    data object Loading : SearchState()
    data class Success(val data: Pair<List<OfferUi>, List<VacancyUi>>) : SearchState()
    data class Error(val message: String) : SearchState()
}