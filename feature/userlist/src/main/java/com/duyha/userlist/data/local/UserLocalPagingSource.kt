package com.duyha.userlist.data.local

import com.duyha.data.local.dao.UserDao
import javax.inject.Inject

/**
 * Local paging source for user pagination.
 */
class UserLocalPagingSource @Inject constructor(
    private val userDao: UserDao,
)  {
    fun pagingSource() = userDao.pagingSource()
}