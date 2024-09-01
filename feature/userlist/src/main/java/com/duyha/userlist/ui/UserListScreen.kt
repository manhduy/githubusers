package com.duyha.userlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.duyha.ui.view.UserItemView

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    onUserClick: (String) -> Unit
) {
    val lazyPagingItems = viewModel.pagingFlow.collectAsLazyPagingItems()
    val refreshing = remember {
        mutableStateOf(false)
    }
    val state = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = {
            lazyPagingItems.refresh()
        }
    )
    when (lazyPagingItems.loadState.refresh ) {
        is LoadState.Loading -> {
            refreshing.value = true
        }
        is LoadState.Error -> {
            refreshing.value = false
        }
        is LoadState.NotLoading ->
            refreshing.value = false
    }
    Box(modifier = Modifier.pullRefresh(state)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            items(
                lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey { it.login }
            ) { index ->
                val item = lazyPagingItems[index]
                item?.let {
                    UserItemView(
                        modifier = Modifier.background(Color.White),
                        login = it.login,
                        avatarUrl = it.avatarUrl,
                        htmlUrl = it.htmlUrl,
                        onUserClick = onUserClick
                    )
                }
            }
        }
        PullRefreshIndicator(refreshing.value, state, Modifier.align(Alignment.TopCenter))
    }
}
