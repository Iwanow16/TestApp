package ru.ivanov23.favorites.presentation.viewmodel

import ru.ivanov23.ui_kit.models.VacancyUi

sealed class FavoriteState {
    data object Loading : FavoriteState()
    data class Success(val data: List<VacancyUi>) : FavoriteState()
    data class Error(val message: String) : FavoriteState()
}