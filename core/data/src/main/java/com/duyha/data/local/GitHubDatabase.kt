package com.duyha.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.duyha.data.local.converter.UserTypeConverter
import com.duyha.data.local.dao.UserDao
import com.duyha.data.local.model.UserDbModel

/**
 * App database.
 */
@Database(
    version = 1,
    entities = [UserDbModel::class]
)
@TypeConverters(UserTypeConverter::class)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}