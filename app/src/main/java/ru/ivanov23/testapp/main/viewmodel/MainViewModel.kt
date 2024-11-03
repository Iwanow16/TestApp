package ru.ivanov23.testapp.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: VacancyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        fetchData()
        loadData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getDataFromRemote()
        }
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                repository.getCountVacancyFlow().collect { data ->
                    _state.value = MainState.Success(data)
                }
            } catch (e: Exception) {
                _state.value = MainState.Error(e.message.orEmpty())
            }
        }
    }
}