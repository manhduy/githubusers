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
            .addHeader("Authorization", "token $_token")
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