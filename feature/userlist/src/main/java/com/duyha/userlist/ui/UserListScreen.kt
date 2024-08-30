package com.duyha.userlist.ui

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.duyha.domain.entity.User
import com.duyha.ui.view.ShimmerAsyncImage

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
    val lazyPagingItems = viewModel.flow.collectAsLazyPagingItems()
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
                        user = it,
                        onUserClick = onUserClick
                    )
                }
            }
        }
        PullRefreshIndicator(refreshing.value, state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemViewPreview() {
    UserItemView(
        user = User(
            id = 1,
            login = "DuyHa",
            avatarUrl = "",
            htmlUrl = "",
            location = "",
            followers = 0,
            following = 0
        ),
        onUserClick = {}
    )
}

@Composable
fun UserItemView(
    modifier: Modifier = Modifier,
    user: User,
    onUserClick: (String) -> Unit
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
        .clickable { onUserClick(user.login) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AvatarView(user.avatarUrl)
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.h6
                )
                Divider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
                Text(
                    text = user.htmlUrl,
                    style = MaterialTheme.typography.caption.copy(color = Color.Blue)
                )
            }
        }
    }

}

@Composable
fun AvatarView(avatarUrl: String?) {
    Box(modifier = Modifier
        .background(
            color = Color.Gray.copy(alpha = 0.1f),
            shape = RoundedCornerShape(16.dp)
        )
        .padding(8.dp)
    ) {
        ShimmerAsyncImage(
            model = avatarUrl,
            contentDescription = "",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )
    }
}
