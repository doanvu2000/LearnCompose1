package com.jin.compose.core.ui.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnCenterItem(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
fun RowCenterItem(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun BoxCenterItem(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        content()
    }
}