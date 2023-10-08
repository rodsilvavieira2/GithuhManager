package com.example.githuhmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    @Named("baseUrl")
    @Singleton
    @Provides
    fun getBaseUrl(): String {
        return "https://api.github.com/"
    }
}
