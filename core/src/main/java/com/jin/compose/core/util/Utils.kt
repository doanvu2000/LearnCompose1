package com.jin.compose.core.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast

fun Any.pretty() = toString().let { toString ->
    var indentLevel = 0
    val indentWidth = 4
    fun padding() = "".padStart(indentLevel * indentWidth)
    buildString {
        var ignoreSpace = false
        toString.onEach { char ->
            when (char) {
                '(', '[', '{' -> {
                    indentLevel++
                    appendLine(char)
                    append(padding())
                }

                ')', ']', '}' -> {
                    indentLevel--
                    appendLine()
                    append(padding())
                    append(char)
                }

                ',' -> {
                    appendLine(char)
                    append(padding())
                    ignoreSpace = true
                }

                ' ' -> {
                    if (!ignoreSpace) append(char)
                    ignoreSpace = false
                }

                else -> append(char)
            }
        }
    }
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.getAppVersionName(): String {
    return try {
        val pm = packageManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val info = pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            info.versionName ?: "1.0"
        } else {
            @Suppress("DEPRECATION")
            val info = pm.getPackageInfo(packageName, 0)
            info.versionName ?: "1.0"
        }
    } catch (e: Exception) {
        "unknown"
    }
}

fun Context.getAppVersionCode(): Long {
    return try {
        val pm = packageManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val info = pm.getPackageInfo(packageName, 0)
            info.longVersionCode
        } else {
            @Suppress("DEPRECATION")
            val info = pm.getPackageInfo(packageName, 0)
            info.versionCode.toLong()
        }
    } catch (e: Exception) {
        -1
    }
}
