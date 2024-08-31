package com.duyha.userdetail.domain.respository

import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */
interface UserRepository {
    fun getUserDetail(login: String): Flow<Result<User>>
}