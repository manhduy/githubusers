package com.duyha.data.mapper

import com.duyha.domain.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author: DuyHa
 * @date: 24/05/2024
 */

/**
 * Common function to make API call and map to Result entity
 * @param dispatcher [Dispatchers.IO] by default
 * @param block [suspend () -> T] function to make API call
 */
fun <T> mapResult(
    dispatcher: kotlinx.coroutines.CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> T
) = flow {
    try {
        val response = block()
        emit(Result.Success(
            data = response
        ))
    } catch (e: Exception) {
        emit(Result.Error(
            e = e
        ))
    }
}.flowOn(dispatcher)