package com.jin.learncompose1.ui.scene.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jin.compose.core.util.getAppVersionName
import com.jin.learncompose1.R
import com.jin.learncompose1.ui.navigation.HOME_SCREEN_ROUTE
import com.jin.learncompose1.ui.navigation.SPLASH_SCREEN_ROUTE
import com.jin.learncompose1.ui.theme.BackgroundColorSplash
import com.jin.learncompose1.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(HOME_SCREEN_ROUTE) {
            popUpTo(SPLASH_SCREEN_ROUTE) { inclusive = true }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColorSplash)
    ) {
        val (imgApp, loading, textTitle, adsDes) = createRefs()

        val radiusPx = with(LocalDensity.current) { 16.dp.toPx() }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.ic_app)
//                .transformations(RoundedCornersTransformation(radiusPx))
                .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(imgApp) {
                    bottom.linkTo(textTitle.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )

        Text(
            modifier = Modifier.constrainAs(textTitle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Center,
            text = stringResource(R.string.txt_welcome),
            color = White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(30.dp)
                .constrainAs(loading) {
                    top.linkTo(textTitle.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, color = White
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
            text = "${stringResource(R.string.txt_author_des)} ${LocalContext.current.getAppVersionName()}",
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}