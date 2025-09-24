package com.solar.learncompose1.data.api

import com.solar.learncompose1.data.session.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val READ_TIME_OUT = 15L
    private const val CONNECT_TIME_OUT = 15L

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client: OkHttpClient =
        OkHttpClient.Builder().readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).connectTimeout(
            CONNECT_TIME_OUT, TimeUnit.SECONDS
        ).addInterceptor(interceptor).build()

    fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    val retrofitService: ApiService by lazy {
        retrofitBuilder().create(ApiService::class.java)
    }
}