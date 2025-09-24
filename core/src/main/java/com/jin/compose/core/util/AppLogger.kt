package com.jin.compose.core.util

import android.util.Log
import com.jin.compose.core.BuildConfig

const val TAG = "AppLogger"

fun isDebugMode() = BuildConfig.DEBUG
//fun isDebugMode() = false

object AppLogger {
    fun i(tag: String, msg: String) {
        if (isDisableLog(tag)) return
        Log.i(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (isDisableLog(tag)) return
        Log.d(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (isDisableLog(tag)) return
        Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (isDisableLog(tag)) return
        Log.e(tag, msg)
    }

    private fun isDisableLog(tag: String): Boolean {
        if (!isDebugMode()) {
            return true
        }
        if (tag.length > 22) {
            return true
        }
        return false
    }
}