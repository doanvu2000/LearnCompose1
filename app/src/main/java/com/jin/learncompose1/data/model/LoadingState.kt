package com.jin.learncompose1.data.model

sealed class LoadingState {
    object Loading : LoadingState()
    object Success : LoadingState()
    data class Error(val error: String) : LoadingState()
}