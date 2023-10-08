package com.example.githuhmanager.api

import com.example.githuhmanager.data.repositories.GithubReposNetworkModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

interface IGithubApiService {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<GithubReposNetworkModel>
}

@Module()
@InstallIn(SingletonComponent::class)
object GitHubApiModule {


    @Singleton
    @Provides
    fun providerGithubApiService(): IGithubApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).build().create(IGithubApiService::class.java)
    }
}

const val BASE_URL = "https://api.github.com/"