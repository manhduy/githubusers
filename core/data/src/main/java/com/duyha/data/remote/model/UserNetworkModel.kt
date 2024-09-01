package com.duyha.data.remote.model

import androidx.annotation.Keep
import com.duyha.data.local.model.UserDbModel
import com.duyha.domain.entity.User

/**
 * User network model.
 */
@Keep
data class UserNetworkModel(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: String,
    val location: String?,
    val followers: Int,
    val following: Int,
)

/**
 * Convert UserNetworkModel to User entity.
 */
fun UserNetworkModel.toEntity() = User(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)

/**
 * Convert UserNetworkModel to UserDbModel entity.
 */
fun UserNetworkModel.toDbModel() = UserDbModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)