package com.duyha.userdetail.ui

import com.duyha.domain.entity.Result
import com.duyha.domain.entity.User

/**
 * @author: DuyHa
 * @date: 31/08/2024
 */
sealed interface UserDetailUiState {
    data class Success(val user: User) : UserDetailUiState
    data class Error(val error: Result.Error<User>) : UserDetailUiState
    data object Loading : UserDetailUiState
}