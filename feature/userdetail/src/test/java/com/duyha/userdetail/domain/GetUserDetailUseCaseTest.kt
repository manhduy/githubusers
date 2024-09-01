package com.duyha.userdetail.domain

import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import com.duyha.userdetail.domain.respository.UserRepository
import com.duyha.userdetail.domain.usecase.GetUserDetailUseCase
import com.google.common.base.Predicates.equalTo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * @author: DuyHa
 * @date: 01/09/2024
 */
class GetUserDetailUseCaseTest {
    private val userRepository = mock<UserRepository>()
    private val getUserDetailUseCase = GetUserDetailUseCase(userRepository)

    @Test
    fun invoke_shouldCallUserRepository() {
        // given
        val login = "duy"
        // when
        getUserDetailUseCase(login)
        // then
        verify(userRepository).getUserDetail(login)
    }

    @Test
    fun invoke_shouldReturnUserDetail() {
        // given
        val login = "duy"
        val user = User(
            id = 1,
            login = "duy",
            avatarUrl = "",
            htmlUrl = "duyha.com",
            location = "Viet Nam",
            followers = 11,
            following = 0
        )
        val result = flow<Result<User>> {
            emit(Result.Success(
                data = user
            ))
        }
        // when
        whenever(userRepository.getUserDetail(login)).thenReturn(result)
        // then
        val actual = getUserDetailUseCase(login)
        assertThat(actual).isEqualTo(result)
    }
}