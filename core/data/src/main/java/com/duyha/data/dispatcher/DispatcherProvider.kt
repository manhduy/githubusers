package com.duyha.data.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author: DuyHa
 * @date: 27/05/2024
 */
interface DispatcherProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}

internal class DispatcherProviderImpl : DispatcherProvider {
    override fun io() = Dispatchers.IO
    override fun main() = Dispatchers.Main
    override fun default() = Dispatchers.Default
    override fun unconfined() = Dispatchers.Unconfined
}