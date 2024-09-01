package com.duyha.userlist.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.duyha.userlist.ui.UserListScreen
import com.duyha.userlist.ui.UserListViewModel

const val USER_LIST_ROUTE = "user_list_route"

/**
 * Composable for user list.
 */
fun NavGraphBuilder.userListScreen(
    onUserClick: (String) -> Unit,
) {
    composable(route = USER_LIST_ROUTE) {
        UserListScreen(
            viewModel = hiltViewModel<UserListViewModel>(),
            onUserClick = onUserClick
        )
    }
}
