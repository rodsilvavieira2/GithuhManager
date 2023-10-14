package com.example.githuhmanager.data.repositories

import com.example.githuhmanager.api.IGithubApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface  IGithubReposRepository {
   suspend fun loadAll(user: String): List<GithubReposNetworkModel>
}

class NetworkGithubReposRepository @Inject constructor (
   private val githubApiService: IGithubApiService
): IGithubReposRepository {
    override suspend fun loadAll(user: String): List<GithubReposNetworkModel> {
        return  githubApiService.getRepos(user)
    }
}


@Module
@InstallIn(SingletonComponent::class)
abstract class GithubReposRepositoryModule  {

    @Singleton
    @Binds
    abstract fun providerGithubReposRepository(impl: NetworkGithubReposRepository): IGithubReposRepository
}