package com.duyha.userdetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duyha.userdetail.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.duyha.domain.entity.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
    private val _userDetailUiState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val userDetailUiState: StateFlow<UserDetailUiState> = _userDetailUiState


    fun getUserDetail(login: String) {
        _userDetailUiState.value = UserDetailUiState.Loading
        viewModelScope.launch {
            getUserDetailUseCase(login).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _userDetailUiState.value = UserDetailUiState.Success(result.data)
                    }
                    is Result.Error -> {
                        _userDetailUiState.value = UserDetailUiState.Error(result)
                    }
                }
            }
        }
    }
}