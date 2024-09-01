package com.duyha.userdetail.data.repository

import com.duyha.data.dispatcher.DispatcherProvider
import com.duyha.data.mapper.mapResult
import com.duyha.data.remote.api.GithubApi
import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import com.duyha.userdetail.domain.respository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [UserRepository].
 */
class UserRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val githubApi: GithubApi
) : UserRepository {
    override fun getUserDetail(login: String): Flow<Result<User>> =
        mapResult(dispatcherProvider.io()) {
        githubApi.getUserDetail(login)
    }
}