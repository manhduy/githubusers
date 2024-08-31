package com.duyha.githubusers.ui

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.duyha.userdetail.navigation.navigateUserDetail
import com.duyha.userdetail.navigation.userDetailScreen
import com.duyha.userlist.navigation.USER_LIST_ROUTE
import com.duyha.userlist.navigation.userListScreen

/**
 * @author: DuyHa
 * @date: 29/08/2024
 */
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
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