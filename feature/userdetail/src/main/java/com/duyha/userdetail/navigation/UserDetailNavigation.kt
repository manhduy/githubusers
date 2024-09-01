package com.duyha.userdetail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.duyha.userdetail.ui.UserDetailScreen
import com.duyha.userdetail.ui.UserDetailViewModel

const val USER_DETAIL_ROUTE = "user_detail_route"
const val USER_DETAIL_LOGIN_ARG = "login"

/**
 * Navigation for user detail.
 */
fun NavController.navigateUserDetail(login: String) =
    navigate("$USER_DETAIL_ROUTE/$login")

/**
 * Composable for user detail.
 */
fun NavGraphBuilder.userDetailScreen() {
    composable(
        route = "$USER_DETAIL_ROUTE/{$USER_DETAIL_LOGIN_ARG}",
        arguments = listOf(navArgument(USER_DETAIL_LOGIN_ARG) { type = NavType.StringType }),
    ) { backStackEntry ->
        UserDetailScreen(
            viewModel = hiltViewModel<UserDetailViewModel>(),
            login = backStackEntry.arguments?.getString(USER_DETAIL_LOGIN_ARG) ?: "",
        )
    }
}
