package ru.ivanov23.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.search.mapper.toUi
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: VacancyRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<SearchState>(SearchState.Loading)
    val state: StateFlow<SearchState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                repository.getDataFlow().collect { data ->
                    val offers = data.first.map { it.toUi() }
                    val vacancies = data.second.map { it.toUi() }
                    _state.value = SearchState.Success(Pair(offers, vacancies))
                }
            } catch (e: Exception) {
                _state.value = SearchState.Error(e.message.orEmpty())
            }
        }
    }

    fun toggleFavorite(vacancyId: String) {
        viewModelScope.launch {
            repository.toggleFavourite(vacancyId)
        }
    }
}