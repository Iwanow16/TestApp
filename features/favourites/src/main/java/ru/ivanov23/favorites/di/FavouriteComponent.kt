package ru.ivanov23.favorites.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import dagger.Component
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.favorites.presentation.fragments.FavoriteFragment
import ru.ivanov23.favorites.presentation.viewmodel.FavoriteModelFactory
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@FavoriteScope
@Component(dependencies = [FavouriteDeps::class])
interface FavouriteComponent {
    @Component.Factory
    interface Factory {
        fun create(repository: FavouriteDeps): FavouriteComponent
    }

    fun inject(fragment: FavoriteFragment)

    fun viewModelFactory(): FavoriteModelFactory
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoriteScope

interface FavouriteDeps {
    val repository: VacancyRepository
}

interface FavoriteDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: FavouriteDeps

    companion object : FavoriteDepsProvider by FavoriteDepsStore
}

object FavoriteDepsStore : FavoriteDepsProvider {
    override var deps: FavouriteDeps by notNull()
}

internal class FavouriteComponentViewModel : ViewModel() {
    val newFavouriteComponent = DaggerFavouriteComponent.factory().create(FavoriteDepsProvider.deps)
}