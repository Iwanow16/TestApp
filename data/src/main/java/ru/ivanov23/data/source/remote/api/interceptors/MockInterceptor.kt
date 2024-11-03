package ru.ivanov23.data.source.remote.api.interceptors

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class MockInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        if (url.contains("api/mock")) {
            val mockData = javaClass.classLoader?.getResource("assets/mock.json")?.readText()
            return if (mockData != null) {
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("OK")
                    .body(mockData.toByteArray().toResponseBody("application/json".toMediaType()))
                    .build()
            } else {
                Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(404)
                    .message("Mock data not found")
                    .body("".toResponseBody(null))
                    .build()
            }
        }

        return chain.proceed(request)
    }
}