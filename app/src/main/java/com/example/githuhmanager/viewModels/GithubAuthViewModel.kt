package com.example.githuhmanager.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.githuhmanager.usecases.auth.ISocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GithubAuthViewModel @Inject constructor(
    private val socialLogin: ISocialLoginUseCase
) : ViewModel() {

    fun onGithubLogin(context: Context) {
        socialLogin.onGithubLogin(context)
    }
}