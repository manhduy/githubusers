package com.duyha.data.remote.header

import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp interceptor for adding headers to requests.
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

    /**
     * Sets the authorization token.
     * @param token The token to set.
     */
    fun setToken(token: String) {
        _token = token
    }

    companion object {
        /**
         * Test token for GitHub API.
         */
        private const val TEST_TOKEN =
            "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"
    }
}
