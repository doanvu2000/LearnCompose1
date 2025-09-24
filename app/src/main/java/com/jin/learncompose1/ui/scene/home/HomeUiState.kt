package com.jin.learncompose1.ui.scene.home

import com.jin.learncompose1.domain.model.DomainApiResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val response: DomainApiResponse? = null
)
