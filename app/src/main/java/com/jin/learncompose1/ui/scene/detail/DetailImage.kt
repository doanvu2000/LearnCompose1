package com.jin.learncompose1.ui.scene.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jin.compose.core.util.toast
import com.jin.learncompose1.ui.theme.White
import com.jin.learncompose1.ui.theme.colorText
import com.jin.learncompose1.ui.widget.TokenImageWithIndicator

@Composable
fun DetailImage(
    navController: NavHostController,
    url: String,
    viewModel: DetailImageViewModel = hiltViewModel<DetailImageViewModel>()
) {
    val ctx = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    // Handle messages with Toast
    LaunchedEffect(uiState.message) {
        uiState.message?.let { message ->
            ctx.toast(message)
            viewModel.clearMessage()
        }
    }

    // Navigate back on success
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.popBackStack()
        }
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (btnBack, detailImage, btnSetWallpaper) = createRefs()

        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .constrainAs(btnBack) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
                .clickable {
                    navController.popBackStack()
                }
                .size(48.dp))

        ElevatedCard(
            modifier = Modifier.constrainAs(detailImage) {
                top.linkTo(btnBack.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(btnSetWallpaper.top, margin = 16.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                }, shape = RoundedCornerShape(12.dp)
        ) {
            TokenImageWithIndicator(
                modifier = Modifier.fillMaxSize(), ctx, url
            )
        }

        ElevatedButton(
            modifier = Modifier.constrainAs(btnSetWallpaper) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            },
            colors = ButtonDefaults.elevatedButtonColors(containerColor = White),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(vertical = 14.dp),
            enabled = !uiState.isLoading,
            onClick = {
                viewModel.setWallpaper(ctx, url)
            }) {
            Text(
                "Set as Wallpaper",
                color = colorText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }

    // Loading Dialog
    if (uiState.isLoading) {
        LoadingDialog()
    }
}

@Composable
private fun LoadingDialog() {
    AlertDialog(
        onDismissRequest = { /* Cannot dismiss while loading */ },
        confirmButton = { },
        title = {
            Text(
                text = "Setting Wallpaper",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        })
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