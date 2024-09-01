package com.duyha.userdetail.ui

import app.cash.turbine.test
import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User
import com.duyha.userdetail.domain.respository.UserRepository
import com.duyha.userdetail.domain.usecase.GetUserDetailUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.whenever

/**
 * Unit test for [UserDetailViewModel]
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var userDetailUseCase: GetUserDetailUseCase

    @Before
    fun setup() {
        userDetailUseCase = mock()
        viewModel = UserDetailViewModel(userDetailUseCase)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getUserDetail_Success_SuccessUserUiState() = runTest {
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
        whenever(userDetailUseCase.invoke(anyOrNull())).thenReturn(result)
        // when
        viewModel.getUserDetail(login)
        // then
        viewModel.userDetailUiState.test {
            val initial = awaitItem()
            assertThat(initial is UserDetailUiState.Loading)
            val success = awaitItem()
            assertThat(initial is UserDetailUiState.Success)
            assertThat((success as UserDetailUiState.Success).user).isEqualTo(user)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun getUserDetail_Error_ErrorUiState() = runTest {
        // given
        val login = "duy"
        val result = flow<Result<User>> {
            emit(Result.Error(Exception()))
        }
        whenever(userDetailUseCase.invoke(anyOrNull())).thenReturn(result)
        // when
        viewModel.getUserDetail(login)
        // then
        viewModel.userDetailUiState.test {
            val initial = awaitItem()
            assertThat(initial is UserDetailUiState.Loading)
            val error = awaitItem()
            assertThat(error is UserDetailUiState.Error)
            cancelAndConsumeRemainingEvents()
        }
    }
}