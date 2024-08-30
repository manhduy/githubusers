package com.duyha.data.network.api

import com.duyha.data.network.model.UserNetworkModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */
interface GithubApi {

    @GET("users")
    suspend fun getUsers(
        @Query("since") page: Int = 0,
        @Query("per_page") perPage: Int = 20
    ): List<UserNetworkModel>

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}