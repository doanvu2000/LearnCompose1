package com.jin.compose.core.ui.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScaffoldSafeDrawing(
    modifier: Modifier = Modifier, content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        content(it)
    }
}

@Composable
fun RootComposeApp(content: @Composable ColumnScope.() -> Unit) {
    ScaffoldSafeDrawing(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}