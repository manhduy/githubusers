package com.duyha.domain.entity

/**
 * User Entity
 */
data class User (
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: String,
    val location: String?,
    val followers: Int,
    val following: Int,
)