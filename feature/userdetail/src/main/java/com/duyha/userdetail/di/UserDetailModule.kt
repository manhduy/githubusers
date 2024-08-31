package com.duyha.userdetail.di

import com.duyha.data.dispatcher.DispatcherProvider
import com.duyha.data.remote.api.GithubApi
import com.duyha.userdetail.data.repository.UserRepositoryImpl
import com.duyha.userdetail.domain.respository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author: DuyHa
 * @date: 31/08/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object UserDetailModule {

    @Provides
    fun provideUserRepository(
        dispatcherProvider: DispatcherProvider,
        githubApi: GithubApi
    ): UserRepository {
        return UserRepositoryImpl(
            dispatcherProvider,
            githubApi
        )
    }
}