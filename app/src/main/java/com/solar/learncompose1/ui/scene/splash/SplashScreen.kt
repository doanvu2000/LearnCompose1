package com.solar.learncompose1.ui.scene.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.solar.learncompose1.ui.navigation.Screen

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate(Screen.Home.route)
//        {
//            popUpTo("splash") { inclusive = true }
//        }
    }

    ColumnCenterItem(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }

}