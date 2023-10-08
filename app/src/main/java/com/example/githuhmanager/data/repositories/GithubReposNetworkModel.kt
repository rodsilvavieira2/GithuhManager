package com.example.githuhmanager.data.repositories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubReposNetworkModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("language")
    val language: String,
    @SerialName("stars")
    val stars: String,
    @SerialName("visibility")
    val visibility: String
)

