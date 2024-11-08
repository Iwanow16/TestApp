package ru.ivanov23.search.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import dagger.Component
import ru.ivanov23.domain.repository.VacancyRepository
import ru.ivanov23.search.presentation.fragments.SearchFragment
import ru.ivanov23.search.presentation.fragments.VacancyListFragment
import ru.ivanov23.search.presentation.fragments.VacancyMainFragment
import ru.ivanov23.search.presentation.viewmodel.SearchModelFactory
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@SearchScope
@Component(dependencies = [SearchDeps::class])
interface SearchComponent {

    @Component.Factory
    interface Factory {
        fun create(repository: SearchDeps): SearchComponent
    }

    fun inject(fragment: SearchFragment)
    fun inject(fragment: VacancyMainFragment)
    fun inject(fragment: VacancyListFragment)

    fun viewModelFactory(): SearchModelFactory
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchScope

interface SearchDeps {
    val repository: VacancyRepository
}

interface SearchDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: SearchDeps

    companion object : SearchDepsProvider by SearchDepsStore
}

object SearchDepsStore : SearchDepsProvider {
    override var deps: SearchDeps by notNull()
}

internal class SearchComponentViewModel : ViewModel() {
    val newSearchComponent = DaggerSearchComponent.factory().create(SearchDepsProvider.deps)
}