package com.example.githuhmanager.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githuhmanager.data.repositories.GithubReposNetworkModel
import com.example.githuhmanager.data.repositories.IGithubReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface GithubReposUiState {
    data class Success(val data: List<GithubReposNetworkModel>) : GithubReposUiState
    object Loading : GithubReposUiState
    object Error : GithubReposUiState
}


@HiltViewModel
class GithubReposViewModel @Inject constructor (
     private val  githubReposRepository: IGithubReposRepository
) : ViewModel() {
    private var _uiState: MutableStateFlow<GithubReposUiState> = MutableStateFlow(GithubReposUiState.Loading)

    val uiState: StateFlow<GithubReposUiState>
        get() = _uiState.asStateFlow()

    init {
        getGitRepos()
    }

    private fun getGitRepos () {
        viewModelScope.launch {
            _uiState.value = GithubReposUiState.Loading

            _uiState.value = try {
                GithubReposUiState.Success(githubReposRepository.loadAll("rodsilvavieira2"))
            } catch (e: IOException) {
                GithubReposUiState.Error
            } catch (e: HttpException) {
                GithubReposUiState.Error
            }
        }
    }
}