package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.learncompose1.ui.theme.White
import com.jin.learncompose1.ui.theme.colorText

@Composable
fun HomeErrorLayout(onClickRetry: () -> Unit) {
    ColumnCenterItem(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 12.dp),
            text = "Error: failed to load data, click to retry",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            color = Color("#ff0000".toColorInt())
        )

        ElevatedButton(
            onClick = onClickRetry,
            modifier = Modifier
                .padding(top = 16.dp),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = White)
        ) {
            Text(
                text = "Retry",
                fontWeight = FontWeight.Bold,
                color = colorText,
                fontSize = 16.sp
            )
        }
    }
}