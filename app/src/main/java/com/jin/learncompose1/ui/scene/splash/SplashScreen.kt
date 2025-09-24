package com.jin.learncompose1.ui.scene.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.jin.learncompose1.R
import com.jin.learncompose1.ui.navigation.HOME_SCREEN_ROUTE
import com.jin.learncompose1.ui.navigation.SPLASH_SCREEN_ROUTE
import com.jin.learncompose1.ui.theme.colorText

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate(HOME_SCREEN_ROUTE) {
            popUpTo(SPLASH_SCREEN_ROUTE) { inclusive = true }
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (loading, textTitle, adsDes) = createRefs()
        CircularProgressIndicator(
            modifier = Modifier
                .size(30.dp)
                .constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Text(
            modifier = Modifier.constrainAs(textTitle) {
                top.linkTo(loading.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Center,
            text = stringResource(R.string.txt_welcome),
            color = colorText,
            fontSize = 22.sp
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(adsDes) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center,
            text = stringResource(R.string.txt_welcome),
            color = colorText,
            fontSize = 14.sp
        )
    }

}