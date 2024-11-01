package ru.ivanov23.data.di

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.ivanov23.data.local.dao.VacancyDao
import ru.ivanov23.data.remote.api.ApiService
import ru.ivanov23.data.remote.api.interceptors.MockInterceptor
import ru.ivanov23.data.repository.VacancyRepositoryIml
import ru.ivanov23.domain.repository.VacancyRepository
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(MockInterceptor())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://mock.com/api/")
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}