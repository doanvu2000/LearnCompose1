package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.jin.compose.core.ui.list.GridListTwo
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.compose.core.util.toast
import com.jin.learncompose1.domain.model.DomainApiResponse
import com.jin.learncompose1.ui.theme.HeaderColor1
import com.jin.learncompose1.ui.theme.HeaderColor2
import com.jin.learncompose1.ui.widget.TokenImageWithIndicator
import com.jin.learncompose1.util.getUrlWithEndPoint

@Composable
fun HomeContent(response: DomainApiResponse?) {
    val context = LocalContext.current

    ColumnCenterItem(
        modifier = Modifier.fillMaxSize()
    ) {
        val phoneSize = response?.phone?.size ?: 0
        val mediumMuseumSize = response?.mediumMuseum?.size ?: 0

        val phoneUrls = response?.phone ?: emptyList()
        val mediumMuseumUrls = response?.mediumMuseum ?: emptyList()

        GridListTwo(modifier = Modifier.fillMaxSize()) {
            //Header Phone Image
            item(span = { GridItemSpan(maxLineSpan) }) {
                HeaderText(text = "Phone Image", color = HeaderColor1)
            }
            //list image phone
            items(phoneSize) { index ->
                val url = phoneUrls.getOrNull(index) ?: ""
                ImageItem(url) {
                    context.toast("click phone item at index: $index")
                }
            }
            //Header Medium Museum Image
            item(span = { GridItemSpan(maxLineSpan) }) {
                HeaderText(text = "Medium Museum", color = HeaderColor2)
            }
            //list image medium museum
            items(mediumMuseumSize) { index ->
                val url = mediumMuseumUrls.getOrNull(index) ?: ""
                ImageItem(url) {
                    context.toast("click medium museum item at index: $index")
                }
            }
        }
    }
}

@Composable
private fun ImageItem(url: String, onClickItem: (() -> Unit)? = null) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(3 / 4f),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        onClick = {
            onClickItem?.invoke()
        }) {
        val ctx = LocalContext.current
        TokenImageWithIndicator(ctx, url.getUrlWithEndPoint())
    }
}

@Composable
private fun HeaderText(text: String, color: Color) {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        text = text,
        color = color,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold
    )
}