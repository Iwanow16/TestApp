package ru.ivanov23.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.favorites.mapper.toUi
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val repository: VacancyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val state: StateFlow<FavoriteState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                repository.getFavouriteVacancy().collect { data ->
                    _state.value = FavoriteState.Success(data.map { it.toUi() })
                }
            } catch (e: Exception) {
                _state.value = FavoriteState.Error(e.message.orEmpty())
            }
        }
    }

    fun toggleFavorite(vacancyId: String) {
        viewModelScope.launch {
            repository.toggleFavourite(vacancyId)
        }
    }
}