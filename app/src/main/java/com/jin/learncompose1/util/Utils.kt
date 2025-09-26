package com.jin.learncompose1.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.jin.learncompose1.data.session.baseUrl

fun String.getUrlWithEndPoint() = baseUrl() + this

fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
    return if (capabilities != null) {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } else false
}