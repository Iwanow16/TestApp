package ru.ivanov23.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Inject

class FavoriteModelFactory @Inject constructor(
    private val repository: VacancyRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == FavouriteViewModel::class.java)
        return FavouriteViewModel(repository) as T
    }
}

