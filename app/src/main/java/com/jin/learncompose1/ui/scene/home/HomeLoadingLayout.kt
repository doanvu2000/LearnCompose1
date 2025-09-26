package com.jin.learncompose1.ui.scene.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun HomeLoadingLayout() {
    var loadingStage by remember { mutableIntStateOf(0) }

    // Animation cho text fade in/out
    val infiniteTransition = rememberInfiniteTransition(label = "loading_animation")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha_animation"
    )

    // Animation cho scale của loading indicator
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_animation"
    )

    // Timer để thay đổi message theo thời gian
    LaunchedEffect(Unit) {
        // Stage 0: 0-3s
        delay(3000)
        loadingStage = 1

        // Stage 1: 3-5s
        delay(2000)
        loadingStage = 2

        // Stage 2: 5-7s
        delay(2000)
        loadingStage = 3

        // Stage 3: 7-10s
        delay(3000)
        loadingStage = 4

        // Stage 4: 10s+
    }

    val loadingMessage = when (loadingStage) {
        0 -> "Đang tải dữ liệu..."
        1 -> "Đang lấy dữ liệu từ server..."
        2 -> "Mạng hơi chậm, đợi xíu nhé! 🐌"
        3 -> "Vẫn đang tải... Kiên nhẫn chút! ⏳"
        else -> "Đảm bảo mạng internet/4-5G ổn định nhé bạn! 📶"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Loading indicator với scale animation
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            strokeWidth = 4.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Text với fade animation
        Text(
            text = loadingMessage,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.graphicsLayer {
                this.alpha = alpha
            }
        )

        // Thêm sub text cho stage cuối
        if (loadingStage >= 4) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Hoặc thử refresh lại trang! 🔄",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.graphicsLayer {
                    this.alpha = alpha * 0.8f
                }
            )
        }
    }
}