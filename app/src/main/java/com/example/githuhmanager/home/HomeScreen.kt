package com.example.githuhmanager.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githuhmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(vertical = 32.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LogoAndMessage()

            Text(
                text = "Gerencie seus repositórios do github de forma simples e rápida",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp
                )
            )

            GithubSubmitButton()
        }
    }
}

@Composable
fun LogoAndMessage() {

    Column {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github_mark_white),
                    contentDescription = "Github logo",
                    modifier = Modifier.size(74.dp, 74.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Github Manager",
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                )
            }
        }
    }
}

@Composable
fun GithubSubmitButton() {
    return Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.github_mark_white),
                contentDescription = "Github logo",
                modifier = Modifier.size(32.dp, 32.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Enter com o github")
        }
    }
}