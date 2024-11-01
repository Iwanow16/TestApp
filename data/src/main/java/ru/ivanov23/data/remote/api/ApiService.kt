package ru.ivanov23.data.remote.api

import retrofit2.http.GET
import ru.ivanov23.data.remote.models.JobDataDto

interface ApiService {

    @GET("mock")
    suspend fun getData(): JobDataDto
}