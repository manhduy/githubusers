package com.duyha.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.duyha.githubusers.ui.AppNavHost
import com.duyha.ui.theme.GitHubUsersTheme
import com.duyha.userdetail.navigation.USER_DETAIL_ROUTE
import com.duyha.userlist.navigation.USER_LIST_ROUTE
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GitHubUsersTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            navController = navController
                        )
                    },
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                ) { padding ->
                    Box(modifier = Modifier
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                    ) {
                        AppNavHost(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

/**
 * Top app bar for the application.
 * @param navController The navigation controller for the application.
 */
@Composable
fun TopAppBar(
    navController: NavController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val title = if (currentRoute?.startsWith(USER_DETAIL_ROUTE) == true)
        stringResource(id = R.string.user_detail)
    else stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (currentRoute != USER_LIST_ROUTE) {
                Icon(modifier = Modifier.clickable { navController.popBackStack() },
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "",
                )
            }
        },
        backgroundColor = Color.White,
    )
}