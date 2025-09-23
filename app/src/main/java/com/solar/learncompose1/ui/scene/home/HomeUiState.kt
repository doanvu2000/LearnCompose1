package com.solar.learncompose1.ui.scene.home

import com.solar.learncompose1.data.model.ApiResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val response: ApiResponse? = null
)
