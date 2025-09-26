package com.jin.learncompose1.ui.scene.detail

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.FileProvider
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
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DetailImageUiState())
    val uiState: StateFlow<DetailImageUiState> = _uiState.asStateFlow()

    fun setWallpaper(
        context: Context, imageUrl: String, wallpaperType: WallpaperType = WallpaperType.HOME
    ) {
        _uiState.value = _uiState.value.copy(
            isLoading = true, message = null, showWallpaperOptions = false
        )

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    val imageLoader = ImageLoader(context)
                    val request = ImageRequest.Builder(context).data(imageUrl).build()

                    val result = imageLoader.execute(request)
                    if (result is SuccessResult) {
                        val image = result.image
                        val drawable = image.asDrawable(context.resources)
                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val wallpaperManager = WallpaperManager.getInstance(context)

                        when (wallpaperType) {
                            WallpaperType.HOME -> {
                                wallpaperManager.setBitmap(
                                    bitmap, null, true, WallpaperManager.FLAG_SYSTEM
                                )
                            }

                            WallpaperType.LOCK -> {
                                wallpaperManager.setBitmap(
                                    bitmap, null, true, WallpaperManager.FLAG_LOCK
                                )
                            }

                            WallpaperType.BOTH -> {
                                wallpaperManager.setBitmap(bitmap)
                            }
                        }
                        true
                    } else {
                        false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    false
                }
            }

            val message = when (wallpaperType) {
                WallpaperType.HOME -> if (result) "Home wallpaper set successfully" else "Failed to set home wallpaper"
                WallpaperType.LOCK -> if (result) "Lock screen wallpaper set successfully" else "Failed to set lock screen wallpaper"
                WallpaperType.BOTH -> if (result) "Wallpaper set for both home and lock screen" else "Failed to set wallpaper"
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false, message = message, isSuccess = result
            )
        }
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }

    fun showWallpaperOptions() {
        _uiState.value = _uiState.value.copy(showWallpaperOptions = true)
    }

    fun hideWallpaperOptions() {
        _uiState.value = _uiState.value.copy(showWallpaperOptions = false)
    }

    fun shareImage(context: Context, imageUrl: String) {
        _uiState.value = _uiState.value.copy(isSharing = true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val imageLoader = ImageLoader(context)
                    val request = ImageRequest.Builder(context).data(imageUrl).build()

                    val result = imageLoader.execute(request)
                    if (result is SuccessResult) {
                        val image = result.image
                        val drawable = image.asDrawable(context.resources)
                        val bitmap = (drawable as BitmapDrawable).bitmap

                        // Save the bitmap to a temporary file
                        val file = File(context.cacheDir, "shared_image.png")
                        FileOutputStream(file).use { out ->
                            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, out)
                        }

                        // Get the URI for the file using FileProvider
                        val uri: Uri = FileProvider.getUriForFile(
                            context, "${context.packageName}.fileprovider", file
                        )

                        // Create the share intent
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, uri)
                            type = "image/png"
                            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        }

                        // Start the share intent
                        context.startActivity(Intent.createChooser(shareIntent, null))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _uiState.value = _uiState.value.copy(isSharing = false)
                }
            }
        }
    }
}

data class DetailImageUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val isSuccess: Boolean = false,
    val showWallpaperOptions: Boolean = false,
    val isSharing: Boolean = false
)

enum class WallpaperType {
    HOME, LOCK, BOTH
}
