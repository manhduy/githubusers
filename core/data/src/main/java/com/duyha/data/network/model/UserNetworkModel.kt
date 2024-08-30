package com.duyha.data.network.model

import androidx.annotation.Keep
import com.duyha.data.database.model.UserDbModel
import com.duyha.domain.entity.User

/**
 * @author: DuyHa
 * @date: 28/08/2024
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

fun UserNetworkModel.toEntity() = User(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)

fun UserNetworkModel.toDbModel() = UserDbModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers,
    following = following,
)