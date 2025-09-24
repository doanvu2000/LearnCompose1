package com.jin.learncompose1.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Setting : Screen("setting")
}