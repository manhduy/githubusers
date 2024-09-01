package com.duyha.userlist.data.local

import com.duyha.data.local.dao.UserDao
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * @author: DuyHa
 * @date: 01/09/2024
 */
class UserLocalPagingSourceTest {
    private val userDao: UserDao = mock()
    private val userLocalPagingSource = UserLocalPagingSource(userDao)

    @Test
    fun pagingSource_ShouldCallUserDaoPagingSource() {
        userLocalPagingSource.pagingSource()
        verify(userDao).pagingSource()
    }
}