package com.example.githuhmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githuhmanager.ui.screens.HomeScreen
import com.example.githuhmanager.ui.screens.WelcomeScreen
import com.example.githuhmanager.ui.theme.GithuhManagerTheme
import com.example.githuhmanager.usecases.auth.ISocialLoginUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var socialLogin: ISocialLoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLinkIntent(intent)

        setContent {
            GithuhManagerTheme(
                dynamicColor = false
            ) {
                GithubManagerApp()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private  fun handleDeepLinkIntent(intent: Intent?) {
        lifecycleScope.launch {
              socialLogin.onGithubLoginCallback(intent)
       }
    }
}


@Composable
fun GithubManagerApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen()
        }

        composable("home") {
            HomeScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }