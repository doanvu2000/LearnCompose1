package com.jin.learncompose1

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), SingletonImageLoader.Factory {

    override fun newImageLoader(ctx: PlatformContext): ImageLoader {
        return ImageLoader.Builder(ctx).memoryCache {
            MemoryCache.Builder().maxSizeBytes(10L * 1024 * 1024) // 10 MB
                .build()
        }.diskCachePolicy(CachePolicy.ENABLED).build()
    }
}