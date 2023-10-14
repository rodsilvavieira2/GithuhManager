package com.example.githuhmanager.data.repositories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubReposNetworkModel(
    @SerialName("id")
    val id: Int,
    @SerialName("full_name")
    val name: String,
    @SerialName("language")
    val language: String?,
    @SerialName("visibility")
    val visibility: String
)

