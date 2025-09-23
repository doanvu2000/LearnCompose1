package com.solar.learncompose1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solar.learncompose1.ui.scene.home.HomeScreen
import com.solar.learncompose1.ui.scene.splash.SplashScreen

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    val startDestination = Screen.Splash.route
    NavHost(navController, startDestination = startDestination) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}