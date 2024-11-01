package ru.ivanov23.search.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import dagger.Component
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.search.presentation.ui.VacancyMainFragment
import ru.ivanov23.search.presentation.viewmodel.SearchModelFactory
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@SearchScope
@Component(dependencies = [RepositoryDeps::class])
interface SearchComponent {

    @Component.Factory
    interface Factory {
        fun create(repository: RepositoryDeps): SearchComponent
    }

    fun inject(fragment: VacancyMainFragment)

    fun viewModelFactory(): SearchModelFactory
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchScope

interface RepositoryDeps {
    val repository: VacancyRepository
}

interface RepositoryDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: RepositoryDeps

    companion object : RepositoryDepsProvider by RepositoryDepsStore
}

object RepositoryDepsStore : RepositoryDepsProvider {
    override var deps: RepositoryDeps by notNull()
}

internal class SearchComponentViewModel : ViewModel() {
    val newSearchComponent = DaggerSearchComponent.factory().create(RepositoryDepsProvider.deps)
}