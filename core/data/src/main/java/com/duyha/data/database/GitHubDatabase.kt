package com.duyha.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.duyha.data.database.converter.UserTypeConverter
import com.duyha.data.database.dao.UserDao
import com.duyha.data.database.model.UserDbModel

/**
 * @author: DuyHa
 * @date: 30/08/2024
 */
@Database(
    version = 1,
    entities = [UserDbModel::class]
)
@TypeConverters(UserTypeConverter::class)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}