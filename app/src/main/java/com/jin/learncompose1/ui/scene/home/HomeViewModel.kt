package com.jin.learncompose1.ui.scene.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.learncompose1.domain.usecase.ApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiUseCase: ApiUseCase
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

            try {
                val response = apiUseCase.getData()

                _homeUiState.update {
                    it.copy(isLoading = false, response = response)
                }
                Log.d(TAG, "getData: $response")
            } catch (e: Exception) {
                e.printStackTrace()
                _homeUiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    fun updateData() {
        viewModelScope.launch {
            try {
                apiUseCase.updateData()
                Log.d(TAG, "updateData: success")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "updateData failed: ${e.message}")
            }
        }
    }
}