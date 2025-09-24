package com.jin.learncompose1.util

import com.jin.compose.core.util.AppLogger

fun logDebug(msg: String) {
    AppLogger.d(Constants.TAG, msg)
}

fun logError(msg: String) {
    AppLogger.e(Constants.TAG, msg)
}

fun logInfo(msg: String) {
    AppLogger.i(Constants.TAG, msg)
}

fun logWarn(msg: String) {
    AppLogger.w(Constants.TAG, msg)
}