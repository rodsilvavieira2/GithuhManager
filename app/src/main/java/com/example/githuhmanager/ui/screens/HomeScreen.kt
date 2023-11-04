package com.example.githuhmanager.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githuhmanager.base.Avatar
import com.example.githuhmanager.data.repositories.GithubReposNetworkModel
import com.example.githuhmanager.viewModels.GithubReposUiState
import com.example.githuhmanager.viewModels.GithubReposViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: GithubReposViewModel = hiltViewModel()
) {
    val repos = viewModel.uiState.collectAsState()

    Scaffold(containerColor = MaterialTheme.colorScheme.background, topBar = {
        HomeAppBar()
    }) { innerPadding ->
        when (repos.value) {
            is GithubReposUiState.Success -> {
                HomeContent(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    list = (repos.value as GithubReposUiState.Success).data
                )
            }

            is GithubReposUiState.Loading -> {
                HomeLoading(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            else -> {
                HomeError(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    TopAppBar(title = {
        Text(text = "Olá, Rodrigo Silva", style = MaterialTheme.typography.bodyLarge)
    }, actions = {
        Avatar(
            uri = "https://avatars.githubusercontent.com/u/92103363?v=4", desc = "Rodrigo Silva"
        )
    })
}

@Composable
fun HomeLoading(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun HomeError(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Error...")
    }
}

@Composable
fun HomeContent(modifier: Modifier, list: List<GithubReposNetworkModel>) {
    LazyColumn(
        modifier = modifier.padding(8.dp, 24.dp),
    ) {
        items(list) {
            HomeCardRepo(message = it.name)
        }
    }
}

@Composable
fun HomeCardRepo(message: String) {
    val opacity = remember { Animatable(1f) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp, 24.dp)
                .fillMaxWidth()
                .pointerInput(
                    Unit
                ) {
                    detectTapGestures(
                        onPress = {
                            opacity.animateTo(
                                targetValue = 0.5f,
                            )

                            awaitRelease()

                            opacity.animateTo(
                                targetValue = 1f,
                            )
                        }
                    )
                }
        ) {
            Column {
                Text(
                    text = message.uppercase()[0] + message.substring(1),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}