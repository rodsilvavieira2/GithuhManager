package com.example.githuhmanager.api

import com.example.githuhmanager.data.repositories.GithubReposNetworkModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

interface IGithubApiService {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<GithubReposNetworkModel>


    @POST("/login/oauth/access_token")
    suspend fun getAuthAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    )
}

@Module()
@InstallIn(SingletonComponent::class)
object GitHubApiModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun providerGithubApiService(): IGithubApiService {
        return Retrofit.Builder().baseUrl("https://github.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
            .create(IGithubApiService::class.java)
    }
}

data class AuthAccessTokenInput(
    val clientId: String,
    val clientSecret: String,
    val code: String
)