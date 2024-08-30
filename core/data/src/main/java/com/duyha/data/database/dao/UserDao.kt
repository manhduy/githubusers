package com.duyha.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.duyha.data.database.model.UserDbModel
import com.duyha.domain.entity.User

/**
 * @author: DuyHa
 * @date: 30/08/2024
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserDbModel>

    @Query("SELECT * FROM users")
    fun pagingSource(): PagingSource<Int, User>

    @Insert
    fun insertAll(users: List<UserDbModel>)

    @Delete
    fun delete(user: UserDbModel)

    @Query("DELETE FROM users")
    fun deleteAll()
}