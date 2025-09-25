package com.jin.learncompose1.ui.widget

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.jin.learncompose1.data.session.token
import com.jin.learncompose1.ui.theme.HeaderColor1
import com.jin.learncompose1.util.logDebug

private fun getHeaderNetwork(): NetworkHeaders {
    return NetworkHeaders.Builder().set("Authorization", "token ${token()}")
        .set("Accept", "application/vnd.github.v3.raw").build()
}

@Composable
fun TokenImage(
    url: String, modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context).data(url).httpHeaders(getHeaderNetwork())
            .memoryCachePolicy(CachePolicy.DISABLED)//not cache to ram
            .diskCachePolicy(CachePolicy.ENABLED)// only cache to disk
            .crossfade(true).build(), contentDescription = null, modifier = modifier
    )
}

@Composable
fun TokenImageWithIndicator(
    modifier: Modifier = Modifier,
    ctx: Context, url: String
) {
    logDebug("url: $url")

    val imageRequestBuilder = ImageRequest.Builder(ctx).data(url)
        .memoryCachePolicy(CachePolicy.DISABLED)//not cache to ram
        .diskCachePolicy(CachePolicy.ENABLED)// only cache to disk
        .crossfade(true)

    val isEnableHeader = false
    if (isEnableHeader) {
        imageRequestBuilder.httpHeaders(getHeaderNetwork())
    }

    SubcomposeAsyncImage(
        model = imageRequestBuilder.build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier,
        loading = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = HeaderColor1
                )
            }
        },
        error = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Build, contentDescription = "Load lỗi"
                )
            }
        })
}