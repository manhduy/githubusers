package com.duyha.userdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duyha.domain.entity.User
import com.duyha.ui.view.UserItemView
import com.duyha.userdetail.R

/**
 * @author: DuyHa
 * @date: 28/08/2024
 */

@Composable
fun UserDetailScreen(
    viewModel: UserDetailViewModel,
    login: String
) {
    LaunchedEffect(true) {
        viewModel.getUserDetail(login)
    }
    val uiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    when (uiState) {
        is UserDetailUiState.Success -> {
            val user = (uiState as UserDetailUiState.Success).user
            UserDetailView(user)
        }
        is UserDetailUiState.Error -> {
            Text(text = "Error")
        }
        UserDetailUiState.Loading -> {
            Text(text = "Loading")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailViewPreview() {
    UserDetailView(
        user = User(
            id = 1,
            login = "duy",
            avatarUrl = "",
            htmlUrl = "duyha.com",
            location = "Viet Nam",
            followers = 11,
            following = 0
        )
    )
}

@Composable
fun UserDetailView(user: User) {
    Column(modifier = Modifier.fillMaxWidth()) {
        UserItemView(
            login = user.login,
            avatarUrl = user.avatarUrl,
            location = user.location,
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)) {
            FollowView(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.ic_users),
                count = user.followers,
                title = stringResource(id = R.string.follower)
            )
            FollowView(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.ic_medal_fill),
                count = user.followers,
                title = stringResource(id = R.string.following)
            )
        }
        BlogView(
            modifier = Modifier.padding(24.dp),
            user.htmlUrl
        )
    }

}

@Composable
fun FollowView(
    modifier: Modifier = Modifier,
    painter: Painter,
    count: Int,
    title: String
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(
                    color = Color.Gray.copy(alpha = 0.6f),
                )
                .padding(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center),
                painter = painter,
                contentDescription = ""
            )
        }
        Text(modifier = Modifier.padding(top = 4.dp),
            text = count.toString(),
            style = MaterialTheme.typography.subtitle1
        )
        Text(modifier = Modifier.padding(top = 4.dp),
            text = title,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun BlogView(
    modifier: Modifier = Modifier,
    htmlUrl: String
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.blog),
            style = MaterialTheme.typography.h4
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = htmlUrl,
            style = MaterialTheme.typography.body2
        )
    }
}
