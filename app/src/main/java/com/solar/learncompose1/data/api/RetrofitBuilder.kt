package com.solar.learncompose1.data.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitBuilder {
    private const val BASE_URL =
        "https://raw.githubusercontent.com/doanvu2000/TestServerAPI1/blob/master/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}