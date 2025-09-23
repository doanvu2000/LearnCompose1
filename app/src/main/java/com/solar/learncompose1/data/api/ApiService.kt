package com.solar.learncompose1.data.api

import com.solar.learncompose1.data.model.ApiResponse
import com.solar.learncompose1.data.session.token
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    companion object {
        private const val ACCEPT = "application/vnd.github.v3.raw"
    }

    private fun getToken(): String {
        return "token " + token()
    }

    @GET("data.json")
    suspend fun getData(
        @Header("Accept") accept: String = ACCEPT,
        @Header("Authorization") authorization: String = getToken()
    ): ApiResponse
}