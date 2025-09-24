package com.jin.learncompose1.data.session

private const val ROOT_LINK = "https://raw.githubusercontent.com"
private const val USER_NAME = "doanvu2000"
private const val REPO_NAME = "TestServerAPI1"
private const val BRANCH_NAME = "master"

fun baseUrl(): String {
    return "${ROOT_LINK}/${USER_NAME}/${REPO_NAME}/${BRANCH_NAME}/"
}

fun token(): String {
    return "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"
}