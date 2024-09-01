package com.duyha.domain.entity

/**
 * Common state of data from repository.
 */
sealed class Result<T> {
    data class Success<T>(
        val data: T
    ) : Result<T>()

    data class Error<T>(
        val e: Throwable
    ) : Result<T>()
}