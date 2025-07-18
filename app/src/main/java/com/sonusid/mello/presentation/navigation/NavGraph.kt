// In file: app/src/main/java/com/sonusid/mello/presentation/navigation/MelloNavGraph.kt

package com.sonusid.mello.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sonusid.mello.presentation.homescreen.HomeScreen // Import HomeScreen

object Routes {
    const val HOME = "home"
    // No longer need CREATE_POST as a separate route
    // const val CREATE_POST = "create_post"
}

@Composable
fun MelloNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onCreatePostClick = {
                    // This now triggers the dialog in HomeScreen, no navigation needed
                    // You might remove this onCreatePostClick if the FAB directly controls the dialog state
                    // or keep it if HomeScreen is also a destination for some other external event.
                    // For now, it's redundant as the FAB directly updates the dialog state.
                }
            )
        }
    }
}