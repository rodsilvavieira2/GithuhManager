package com.example.githuhmanager.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githuhmanager.base.Avatar
import com.example.githuhmanager.viewModels.GithubReposViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: GithubReposViewModel = hiltViewModel()
) {
    val repos = viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(text = "Olá, Rodrigo Silva", style = MaterialTheme.typography.bodyLarge)
        },
        actions = {
            Avatar(
                uri = "https://avatars.githubusercontent.com/u/92103363?v=4",
                desc = "Rodrigo Silva"
            )
        }
    )
}