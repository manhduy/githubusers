package com.duyha.data.remote.header

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp interceptor for adding headers to requests.
 */
class HeaderInterceptor : Interceptor {
    private var _token: String = decodeTestToken()

    private fun decodeTestToken(): String {
        val byteArray = Base64.decode(TEST_TOKEN, Base64.DEFAULT)
        return String(byteArray)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "token $_token")
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
         * Test token for GitHub API. It will be expired in the future.
         */
        private const val TEST_TOKEN =
            "Z2l0aHViX3BhdF8xMUFDTzZDVlkwSFFEMTdIU0FDQ0RXX2lxWk5Sd3l1cER3T05xMFVwTXZEZ3RTUzI1THhhNzVCaVV5ZGs5Sjhzc21PQ0dLQzJNNjZ3MzFvR1RR"
    }
}
