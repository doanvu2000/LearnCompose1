package com.jin.learncompose1.util

import com.jin.learncompose1.data.session.baseUrl

fun String.getUrlWithEndPoint() = baseUrl() + this