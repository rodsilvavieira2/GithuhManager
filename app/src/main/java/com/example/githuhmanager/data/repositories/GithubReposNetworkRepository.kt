package com.example.githuhmanager.data.repositories

import com.example.githuhmanager.api.IGithubApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

interface  IGithubReposRepository {
   suspend fun loadAll(user: String): List<GithubReposNetworkModel>;
}

class NetworkGithubReposRepository constructor (
   private val githubApiService: IGithubApiService
): IGithubReposRepository {
    override suspend fun loadAll(user: String): List<GithubReposNetworkModel> {
        return githubApiService.getRepos(user)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object  GithubReposRepositoryModule  {
    @Singleton
    @Provides
    fun providerGithubReposRepository(githubApiService: IGithubApiService): IGithubReposRepository {
        return NetworkGithubReposRepository(githubApiService)
    }
}