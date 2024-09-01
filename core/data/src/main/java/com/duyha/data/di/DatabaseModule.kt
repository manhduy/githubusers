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
 * Hilt module for providing database dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Provides the GitHub database.
     * @param context The application context.
     * @return The GitHub database.
     */
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