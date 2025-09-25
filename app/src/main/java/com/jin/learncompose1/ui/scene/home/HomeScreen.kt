package com.jin.learncompose1.ui.scene.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jin.learncompose1.data.model.LoadingState

@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    val response by remember {
        derivedStateOf {
            homeUiState.response
        }
    }

    when (homeUiState.loadingState) {
        is LoadingState.Error -> {
            HomeErrorLayout {
                viewModel.getData()
            }
        }

        LoadingState.Loading -> {
            HomeLoadingLayout()
        }

        LoadingState.Success -> {
            HomeContent(response)
        }
    }
}