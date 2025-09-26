package com.jin.learncompose1.ui.scene.detail

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import coil3.asDrawable
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailImageUiState())
    val uiState: StateFlow<DetailImageUiState> = _uiState.asStateFlow()

    fun setWallpaper(context: Context, imageUrl: String) {
        _uiState.value = _uiState.value.copy(isLoading = true, message = null)

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    val imageLoader = ImageLoader(context)
                    val request = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .build()

                    val result = imageLoader.execute(request)
                    if (result is SuccessResult) {
                        val image = result.image
                        val drawable = image.asDrawable(context.resources)
                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val wallpaperManager = WallpaperManager.getInstance(context)
                        wallpaperManager.setBitmap(bitmap)
                        true
                    } else {
                        false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = if (result) "Wallpaper set successfully" else "Failed to set wallpaper",
                isSuccess = result
            )
        }
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }
}

data class DetailImageUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val isSuccess: Boolean = false
)
