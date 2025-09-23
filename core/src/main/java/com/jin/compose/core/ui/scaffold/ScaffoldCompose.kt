package com.jin.compose.core.ui.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
        modifier = modifier, contentWindowInsets = WindowInsets.safeDrawing
    ) {
        content(it)
    }
}

@Composable
fun RootColumnApp(content: @Composable ColumnScope.() -> Unit) {
    ScaffoldSafeDrawing(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun RootRowApp(content: @Composable RowScope.() -> Unit) {
    ScaffoldSafeDrawing(Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun RootBoxApp(content: @Composable BoxScope.() -> Unit) {
    ScaffoldSafeDrawing(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun RootApp(content: @Composable () -> Unit) {
    RootBoxApp { content() }
}