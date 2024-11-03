package ru.ivanov23.testapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ivanov23.data.di.DatabaseModule
import ru.ivanov23.data.di.NetworkModule
import ru.ivanov23.data.di.RepositoryModule
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.favorites.di.FavouriteDeps
import ru.ivanov23.search.di.SearchDeps
import ru.ivanov23.testapp.main.MainActivity
import ru.ivanov23.testapp.main.viewmodel.MainViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent : SearchDeps, FavouriteDeps {

    override val repository: VacancyRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

    fun viewModelFactory(): MainViewModelFactory
}