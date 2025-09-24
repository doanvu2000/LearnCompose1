package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.compose.core.util.toast
import com.jin.learncompose1.domain.model.DomainApiResponse
import com.jin.learncompose1.ui.theme.colorText

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    text = "Phone Image",
                    color = colorText
                )
            }

            items(phoneSize) { index ->
                val url = phoneUrls.getOrNull(index) ?: ""
                ItemPhoneImage(url) {
                    context.toast("click phone item at index: $index")
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    text = "Medium Museum",
                    color = colorText
                )
            }

            items(mediumMuseumSize) { index ->
                val url = mediumMuseumUrls.getOrNull(index) ?: ""
                ItemPhoneImage(url) {
                    context.toast("click medium museum item at index: $index")
                }
            }
        }
    }
}

@Composable
private fun ItemPhoneImage(url: String, onClickItem: (() -> Unit)? = null) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        onClick = {
            onClickItem?.invoke()
        }) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            text = "Phone Image: $url",
            color = colorText,
            fontSize = 16.sp
        )
    }
}

//@Preview
//@Composable
//fun HomeContentPreview() {
//    HomeContent(null)
//}