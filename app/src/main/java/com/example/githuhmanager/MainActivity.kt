package com.example.githuhmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githuhmanager.ui.screens.HomeScreen
import com.example.githuhmanager.ui.screens.WelcomeScreen
import com.example.githuhmanager.ui.theme.GithuhManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithuhManagerTheme(
                dynamicColor = false
            ) {
                GithubManagerApp()
            }
        }
    }
}

@Composable
fun GithubManagerApp() {
    val navController =   rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onGoToHomeScreen = {
                navController.navigateSingleTopTo("home")
            })
        }

        composable("home") {
            HomeScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }