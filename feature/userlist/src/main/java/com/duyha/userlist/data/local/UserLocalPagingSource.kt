package com.duyha.userlist.data.local

import com.duyha.data.local.dao.UserDao
import javax.inject.Inject

/**
 * @author: DuyHa
 * @date: 29/08/2024
 */
class UserLocalPagingSource @Inject constructor(
    private val userDao: UserDao,
)  {
    fun pagingSource() = userDao.pagingSource()
}