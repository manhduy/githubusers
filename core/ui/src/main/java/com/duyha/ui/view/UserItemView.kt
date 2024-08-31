package com.duyha.ui.view

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duyha.ui.R

/**
 * @author: DuyHa
 * @date: 31/08/2024
 */
@Preview(showBackground = true)
@Composable
fun UserItemViewPreview() {
    UserItemView(
        login = "duy",
        avatarUrl = "",
        htmlUrl = "duyha.com",
        location = "Viet Nam",
        onUserClick = {}
    )
}

@Composable
fun UserItemView(
    modifier: Modifier = Modifier,
    login: String,
    avatarUrl: String?,
    htmlUrl: String? = null,
    location: String? = null,
    onUserClick: ((String) -> Unit)? = null
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
        .clickable(
            enabled = onUserClick != null,
            onClick = {
                onUserClick?.invoke(login)
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AvatarView(avatarUrl)
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    text = login,
                    style = MaterialTheme.typography.h6
                )
                Divider(modifier = Modifier.padding(top = 12.dp))
                htmlUrl?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption.copy(color = Color.Blue),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
                location?.let {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.map_pin_fill),
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 2.dp)
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body2.copy(color = Color.Gray),
                        )
                    }
                }
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