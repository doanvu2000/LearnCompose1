package com.jin.learncompose1.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Setting : Screen("setting")
}

val SPLASH_SCREEN_ROUTE = Screen.Splash.route
val HOME_SCREEN_ROUTE = Screen.Home.route
val SETTING_SCREEN_ROUTE = Screen.Setting.route