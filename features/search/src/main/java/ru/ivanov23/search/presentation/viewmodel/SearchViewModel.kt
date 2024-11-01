package ru.ivanov23.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ivanov23.domain.usecases.GetVacancyUseCase
import ru.ivanov23.search.mapper.toUi
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val vacancyUseCase: GetVacancyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<VacancyState>(VacancyState.Loading)
    val state: StateFlow<VacancyState> = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                val data = vacancyUseCase()
                val offers = data.first.map { it.toUi() }
                val vacancies = data.second.map { it.toUi() }
                _state.value = VacancyState.Success(Pair(offers, vacancies))
            } catch (e: Exception) {
                _state.value = VacancyState.Error(e.message.orEmpty())
            }
        }
    }
}