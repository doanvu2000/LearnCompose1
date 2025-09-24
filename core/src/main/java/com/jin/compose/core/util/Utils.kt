package com.jin.compose.core.util

import android.content.Context
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