package com.duyha.githubusers.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.duyha.userdetail.navigation.navigateUserDetail
import com.duyha.userdetail.navigation.userDetailScreen
import com.duyha.userlist.navigation.USER_LIST_ROUTE
import com.duyha.userlist.navigation.userListScreen

/**
 * App navigation host.
 * @param modifier The modifier to be applied to the navigation host.
 * @param navController The navigation controller of the application.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = USER_LIST_ROUTE,
    ) {
        userListScreen(
            onUserClick = { login ->
                navController.navigateUserDetail(login)
            }
        )
        userDetailScreen()
    }
}