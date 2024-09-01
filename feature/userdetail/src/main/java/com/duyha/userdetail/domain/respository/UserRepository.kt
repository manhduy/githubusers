package com.duyha.userdetail.domain.respository

import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * A repository for getting user detail.
 */
interface UserRepository {
    fun getUserDetail(login: String): Flow<Result<User>>
}