package com.example.githuhmanager.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githuhmanager.data.repositories.GithubReposNetworkModel
import com.example.githuhmanager.data.repositories.IGithubReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface GithubReposUiState {
    data class Success(val repos: List<GithubReposNetworkModel>) : GithubReposUiState
    object Loading : GithubReposUiState
    object Error : GithubReposUiState
}


@HiltViewModel
class GithubReposViewModel @Inject constructor (
    private val networkGithubReposRepository: IGithubReposRepository
) : ViewModel() {
    var uiState: GithubReposUiState by mutableStateOf(GithubReposUiState.Loading)
        private set

    init {
        getGitRepos()
    }

    fun getGitRepos () {
        viewModelScope.launch {
            uiState = GithubReposUiState.Loading

            uiState = try {
                GithubReposUiState.Success(networkGithubReposRepository.loadAll("rodsilvavieira2"))
            } catch (e: IOException) {
                GithubReposUiState.Error
            } catch (e: HttpException) {
                GithubReposUiState.Error
            }
        }
    }
}