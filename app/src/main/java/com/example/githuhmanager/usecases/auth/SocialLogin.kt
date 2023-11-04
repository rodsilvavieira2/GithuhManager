package com.example.githuhmanager.usecases.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.githuhmanager.api.IGithubApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface ISocialLoginUseCase {
    fun onGithubLogin(context: Context)

    suspend fun onGithubLoginCallback(intent: Intent?)
}

class SocialLoginUseCase @Inject constructor(
    private val githubApiService: IGithubApiService
) : ISocialLoginUseCase {
    override fun onGithubLogin(context: Context) {
        val clientID = "4cfe15961ebdbc216b03"

        val url = "https://github.com/login/oauth/authorize?client_id=$clientID&scope=user,repo"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        context.startActivity(intent)
    }

    override suspend fun onGithubLoginCallback(intent: Intent?) {
        intent?.data?.let { uri ->
            if ("githuhmanager" == uri.scheme && "oauth_callback" == uri.host) {
                val code = uri.getQueryParameter("code")
                val error = uri.getQueryParameter("error")
                if (code != null) {
                    val clientID = "4cfe15961ebdbc216b03"
                    val clientSecret = "2cfb903b8208ed207944039f53142471f5bf5a1a"
                    val token = githubApiService.getAuthAccessToken(
                        clientID,
                        clientSecret,
                        code
                    )
                    println(token)
                } else if (error != null) {
                    // Handle the error scenario
                    // Display an error message or take appropriate action
                }
            }
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SocialLoginUseCaseModule {

    @Singleton
    @Binds
    abstract fun providerSocialLoginUseCase(impl: SocialLoginUseCase): ISocialLoginUseCase
}