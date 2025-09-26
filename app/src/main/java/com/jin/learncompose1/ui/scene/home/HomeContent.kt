package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.learncompose1.domain.model.DomainApiResponse
import com.jin.learncompose1.ui.theme.HeaderColor1
import com.jin.learncompose1.ui.theme.HeaderColor2
import com.jin.learncompose1.ui.widget.TokenImageWithIndicator
import com.jin.learncompose1.util.getUrlWithEndPoint

@Composable
fun HomeContent(response: DomainApiResponse?, onClickItem: ((url: String) -> Unit)? = null) {
    ColumnCenterItem(
        modifier = Modifier.fillMaxSize()
    ) {
        val phoneSize = response?.phone?.size ?: 0
        val mediumMuseumSize = response?.mediumMuseum?.size ?: 0

        val phoneUrls = response?.phone ?: emptyList()
        val mediumMuseumUrls = response?.mediumMuseum ?: emptyList()

        val gridState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            //Header Phone Image
            item(span = { GridItemSpan(maxLineSpan) }) {
                HeaderText(text = "Phone Image", color = HeaderColor1)
            }
            //list image phone
            items(items = phoneUrls, key = { it }) { url ->
                ImageItem(url) {
                    onClickItem?.invoke(url.getUrlWithEndPoint())
                }
            }
            //Header Medium Museum Image
            item(span = { GridItemSpan(maxLineSpan) }) {
                HeaderText(text = "Medium Museum", color = HeaderColor2)
            }
            //list image medium museum
            items(items = mediumMuseumUrls, key = { it }) { url ->
                ImageItem(url) {
                    onClickItem?.invoke(url.getUrlWithEndPoint())
                }
            }
        }
    }
}

@Composable
private fun ImageItem(url: String, onClickItem: (() -> Unit)? = null) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3 / 4f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        onClick = {
            onClickItem?.invoke()
        }) {
        val ctx = LocalContext.current
        TokenImageWithIndicator(
            modifier = Modifier.fillMaxSize(),
            ctx,
            url.getUrlWithEndPoint()
        )
    }
}

@Composable
private fun HeaderText(text: String, color: Color) {
    Text(
        modifier = Modifier
            .fillMaxSize(),
        text = text,
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold
    )
}