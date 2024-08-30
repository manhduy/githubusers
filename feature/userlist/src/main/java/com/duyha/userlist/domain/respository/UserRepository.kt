package com.duyha.userlist.domain.respository

import com.duyha.domain.entity.User

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */
interface UserRepository {
    fun getUserList(): List<User>
}