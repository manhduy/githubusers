package com.duyha.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duyha.domain.entity.User

/**
 * Data class representing a user in the database.
 */
@Entity(tableName = "users")
data class UserDbModel(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: String,
    val location: String?,
    val followers: Int,
    val following: Int,
)

fun UserDbModel.toEntity() = User(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)
