package ru.ivanov23.testapp.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val repository: VacancyRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(repository) as T
    }
}

