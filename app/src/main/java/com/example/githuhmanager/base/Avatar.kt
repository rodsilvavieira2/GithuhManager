package com.example.githuhmanager.base

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Avatar(
    uri: String, desc: String
) {
    AsyncImage(modifier = Modifier.size(44.dp, 44.dp).clip(CircleShape), model = uri, contentDescription = desc)
}