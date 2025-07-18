package com.sonusid.mello.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sonusid.mello.presentation.createpost.CreatePostScreen
import com.sonusid.mello.presentation.homescreen.HomeScreen

object Routes {
    const val HOME = "home"
    const val CREATE_POST = "create_post"
}

@Composable
fun MelloNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onCreatePostClick = {
                    navController.navigate(Routes.CREATE_POST)
                }
            )
        }
        composable(Routes.CREATE_POST) {
            CreatePostScreen(
                onPostCreated = {
                    navController.popBackStack() // Navigate back to Home
                }
            )
        }
    }
}
