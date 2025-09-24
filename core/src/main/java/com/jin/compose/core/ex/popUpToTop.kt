package com.jin.compose.core.ex

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable

/**
 * Pops up to the top of the navigation stack.
 *
 * @param navController The [NavController] used to navigate.
 */
fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
    }
}

/**
 *  Navigate to a route and optionally pop up to the top of the navigation stack.
 *
 *  @param route The route to navigate to.
 *  @param isPopupTop If true, pop up to the top of the navigation stack after navigating. Default is true.
 */
fun NavController.navigatePopUpTop(route: String, isPopupTop: Boolean = true) {
    val nav = this
    navigate(route) {
        if (isPopupTop) {
            popUpToTop(nav)
        }
    }
}

/**
 * Defines the enter transition for composable navigation.
 *
 * The transition combines a fade-in effect with a slide-in from the start of the container.
 *
 * @return An [EnterTransition] defining the combined animation.
 */
val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
    fadeIn(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}

/**
 * Defines the exit transition for a composable within a navigation graph.
 *
 * The transition consists of a fade out and a slide out animation.
 * - The fade out animation lasts for 300 milliseconds with linear easing.
 * - The slide out animation also lasts for 300 milliseconds, using ease out easing,
 *   and slides the content out towards the end of the container.
 */
val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
    fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}

/**
 * Defines a composable destination in the navigation graph with custom animations.
 *
 * @param route The route for the destination. This should be a unique string within the graph.
 * @param enterAnimation A lambda expression that defines the enter transition animation for the composable destination.
 *                       It takes an `AnimatedContentTransitionScope<NavBackStackEntry>` as its receiver and returns an `EnterTransition?`.
 *                       If not provided, it defaults to the `enterTransition` defined in this file.
 * @param exitAnimation A lambda expression that defines the exit transition animation for the composable destination.
 *                      It takes an `AnimatedContentTransitionScope<NavBackStackEntry>` as its receiver and returns an `ExitTransition?`.
 *                      If not provided, it defaults to the `exitTransition` defined in this file.
 * @param content A composable lambda that defines the UI content for the destination.
 *                It takes an `AnimatedContentScope` as its receiver and a `NavBackStackEntry` as a parameter,
 *                providing access to the current navigation state.
 */
fun NavGraphBuilder.composableWithAnimation(
    route: String,
    enterAnimation: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = enterTransition,
    exitAnimation: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = exitTransition,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) = composable(
    route = route,
    enterTransition = enterAnimation,
    exitTransition = exitAnimation,
    content = content
)