package com.solar.learncompose1.ui.scene.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solar.learncompose1.data.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrofitService: ApiService
) : ViewModel() {

    private val TAG = "doanvv"

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(isLoading = true)
            }
            val response = retrofitService.getData()
            if (response.phone.isNotEmpty() && response.medium_museum.isNotEmpty()) {
                _homeUiState.update {
                    it.copy(isLoading = false, response = response)
                }
            }
            Log.d(TAG, "getData: $response")
        }
    }
}