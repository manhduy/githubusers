package com.duyha.data.remote.api

import com.duyha.data.remote.model.UserNetworkModel
import com.duyha.domain.entity.User
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("users/{login}")
    suspend fun getUserDetail(
        @Path("login") login: String
    ): User

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}