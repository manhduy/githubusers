package com.duyha.data.network.auth

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author: DuyHa
 * @date: 29/08/2024
 */
class AuthInterceptor() : Interceptor {
    private var _token: String = TEMP_TOKEN
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "token $_token")
            .build()
        return chain.proceed(newRequest)
    }

    fun setToken(token: String) {
        _token = token
    }

    companion object {
        private const val TEMP_TOKEN =
            "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"
    }
}