package com.duyha.data.di

import android.content.Context
import androidx.room.Room
import com.duyha.data.database.GitHubDatabase
import com.duyha.data.database.dao.UserDao
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
}