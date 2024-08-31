package com.duyha.data.di

import android.content.Context
import androidx.room.Room
import com.duyha.data.local.GitHubDatabase
import com.duyha.data.local.dao.UserDao
import com.duyha.data.dispatcher.DispatcherProvider
import com.duyha.data.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author: DuyHa
 * @date: 30/08/2024
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesGitHubDatabase(
        @ApplicationContext context: Context,
    ): GitHubDatabase = Room.databaseBuilder(
        context,
        GitHubDatabase::class.java,
        "github",
    ).build()

    @Provides
    fun provideUserDao(
        database: GitHubDatabase
    ): UserDao = database.userDao()

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()
}