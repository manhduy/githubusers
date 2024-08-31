package com.duyha.domain.entity

/**
 * @author: DuyHa
 * @date: 23/05/2024
 */
sealed class Result<T> {
    data class Success<T>(
        val data: T
    ) : Result<T>()

    data class Error<T>(
        val e: Throwable
    ) : Result<T>()
}