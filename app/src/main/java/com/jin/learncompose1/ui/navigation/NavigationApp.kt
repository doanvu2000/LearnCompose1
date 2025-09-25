package com.jin.learncompose1.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.learncompose1.ui.scene.detail.DetailImage
import com.jin.learncompose1.ui.scene.home.HomeScreen
import com.jin.learncompose1.ui.scene.splash.SplashScreen

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    val startDestination = SPLASH_SCREEN_ROUTE

    NavHost(navController, startDestination = startDestination) {
        composable(SPLASH_SCREEN_ROUTE) {
            SplashScreen(navController)
        }

        composable(HOME_SCREEN_ROUTE) {
            HomeScreen(navController)
        }

        composable(DETAIL_IMAGE_SCREEN_ROUTE) {
            val url = remember(it) {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("url")
            }
            if (url != null) {
                DetailImage(navController, url)
            } else {
                Text("No data")
            }
        }
    }
}