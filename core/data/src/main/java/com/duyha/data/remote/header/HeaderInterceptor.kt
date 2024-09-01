package com.duyha.data.remote.header

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author: DuyHa
 * @date: 29/08/2024
 */
class HeaderInterceptor : Interceptor {
    private var _token: String = TEST_TOKEN
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $_token")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build()
        return chain.proceed(newRequest)
    }

    fun setToken(token: String) {
        _token = token
    }

    companion object {
        private const val TEST_TOKEN =
            "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"
    }
}
