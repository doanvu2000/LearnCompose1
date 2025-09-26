package com.jin.learncompose1.data.api

import android.content.Context
import com.jin.learncompose1.data.session.baseUrl
import com.jin.learncompose1.util.isNetworkAvailable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val READ_TIME_OUT = 60L
    private const val CONNECT_TIME_OUT = 60L

    fun provideRetrofit(context: Context): Retrofit {
        val cacheSize = 10L * 1024 * 1024 // 10 MB
        val cache = Cache(File(context.cacheDir, "http_cache"), cacheSize)

        val offlineInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!context.isNetworkAvailable()) {
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=86400") // 1 ngày
                    .build()
            }
            chain.proceed(request)
        }

        val onlineInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            response.newBuilder().header("Cache-Control", "public, max-age=60") // cache 60 giây
                .build()
        }

        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder().cache(cache)
            .addInterceptor(offlineInterceptor)    // đọc cache khi offline
            .addNetworkInterceptor(onlineInterceptor) // ghi cache khi online
            .addInterceptor(loggerInterceptor).connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).build()

        return Retrofit.Builder().baseUrl(baseUrl()).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}