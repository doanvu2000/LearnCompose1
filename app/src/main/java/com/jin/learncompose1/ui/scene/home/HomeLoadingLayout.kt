package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jin.compose.core.ui.scaffold.ColumnCenterItem

@Composable
fun HomeLoadingLayout() {
    ColumnCenterItem(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }
}