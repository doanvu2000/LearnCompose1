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
    val startDestination = Screen.Splash.route
    NavHost(navController, startDestination = startDestination) {
        composableWithAnimation(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composableWithAnimation(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}