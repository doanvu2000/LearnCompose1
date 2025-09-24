package com.jin.learncompose1.ui.scene.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jin.compose.core.ui.scaffold.ColumnCenterItem
import com.jin.compose.core.ui.scaffold.RowCenterItem
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

            ColumnCenterItem {
                Text(text = "Error: ${(homeUiState.loadingState as LoadingState.Error).error}")
            }
        }

        LoadingState.Loading -> {
            ColumnCenterItem(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }

        LoadingState.Success -> {
            RowCenterItem(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    text = "${response?.phone ?: ""} ${response?.mediumMuseum ?: ""}"
                )
            }
        }
    }

    if (homeUiState.isLoading) {
        ColumnCenterItem(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.size(30.dp))
        }
    } else {
        RowCenterItem(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                text = "${response?.phone ?: ""} ${response?.mediumMuseum ?: ""}"
            )
        }
    }
}