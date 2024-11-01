package ru.ivanov23.data.di

import dagger.Module
import dagger.Provides
import ru.ivanov23.data.local.dao.VacancyDao
import ru.ivanov23.data.remote.api.ApiService
import ru.ivanov23.data.repository.VacancyRepositoryIml
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideVacancyRepository(apiService: ApiService, dao: VacancyDao): VacancyRepository {
        return VacancyRepositoryIml(apiService, dao)
    }
}