package com.duyha.userdetail.domain.usecase

import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import com.duyha.userdetail.domain.respository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting user detail.
 */
class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(login: String): Flow<Result<User>> {
        return userRepository.getUserDetail(login)
    }
}