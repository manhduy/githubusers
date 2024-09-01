package com.duyha.userlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.duyha.userlist.data.local.UserLocalPagingSource
import com.duyha.userlist.data.remote.UserRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */
@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val localPagingSource: UserLocalPagingSource,
    private val remoteMediator: UserRemoteMediator,
) : ViewModel() {
    val pagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 2,
            initialLoadSize = 20
        ),
        initialKey = 0,
        remoteMediator = remoteMediator
    ) {
        localPagingSource.pagingSource()
    }.flow
        .cachedIn(viewModelScope)
}