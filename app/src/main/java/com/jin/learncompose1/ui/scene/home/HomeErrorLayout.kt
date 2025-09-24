package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.learncompose1.data.model.LoadingState

@Composable
fun HomeErrorLayout(loadingState: LoadingState) {
    ColumnCenterItem {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Error: ${(loadingState as LoadingState.Error).error}"
        )
    }
}