package ru.ivanov23.testapp.main.viewmodel

sealed class MainState {
    data object Loading : MainState()
    data class Success(val data: Int) : MainState()
    data class Error(val message: String) : MainState()
}