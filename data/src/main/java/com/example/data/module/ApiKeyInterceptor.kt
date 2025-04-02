package com.example.data.module

import com.example.domain.util.Constant
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // 기존 URL에 apiKey 추가
        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", Constant.API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}