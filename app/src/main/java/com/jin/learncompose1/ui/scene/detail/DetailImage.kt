package com.jin.learncompose1.ui.scene.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.jin.learncompose1.ui.widget.TokenImageWithIndicator

@Composable
fun DetailImage(navController: NavHostController, url: String) {
    val ctx = LocalContext.current
    ConstraintLayout {
        val (btnBack, detailImage) = createRefs()

        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .constrainAs(btnBack) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
                .clickable {
                    navController.popBackStack()
                }
                .size(48.dp)
                .padding(8.dp))

        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .constrainAs(detailImage) {
                    fullWidthBelow(btnBack, margin = 16.dp)
                }, shape = RoundedCornerShape(12.dp)
        ) {
            TokenImageWithIndicator(
                modifier = Modifier.fillMaxSize(), ctx, url
            )
        }
    }
}

fun ConstrainScope.fullWidthBelow(
    anchor: ConstrainedLayoutReference, margin: Dp = 16.dp
) {
    top.linkTo(anchor.bottom, margin)
    start.linkTo(parent.start, margin)
    end.linkTo(parent.end, margin)
    bottom.linkTo(parent.bottom, margin)
}

fun ConstrainScope.centerInParent() {
    top.linkTo(parent.top)
    bottom.linkTo(parent.bottom)
    start.linkTo(parent.start)
    end.linkTo(parent.end)
}