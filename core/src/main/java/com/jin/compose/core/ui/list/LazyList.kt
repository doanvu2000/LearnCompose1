package com.jin.compose.core.ui.list

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GridListTwo(
    modifier: Modifier = Modifier, isVertical: Boolean = true, content: LazyGridScope.() -> Unit
) {
    if (isVertical) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) { content() }
    } else {
        LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = modifier) { content() }
    }
}