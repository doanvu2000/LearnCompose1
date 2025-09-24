package com.jin.learncompose1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jin.compose.core.ex.composableWithAnimation
import com.jin.learncompose1.ui.scene.home.HomeScreen
import com.jin.learncompose1.ui.scene.splash.SplashScreen

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    val startDestination = SPLASH_SCREEN_ROUTE

    NavHost(navController, startDestination = startDestination) {
        composableWithAnimation(SPLASH_SCREEN_ROUTE) {
            SplashScreen(navController)
        }

        composableWithAnimation(HOME_SCREEN_ROUTE) {
            HomeScreen(navController)
        }
    }
}