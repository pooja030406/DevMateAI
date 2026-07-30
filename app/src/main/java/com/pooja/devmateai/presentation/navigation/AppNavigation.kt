package com.pooja.devmateai.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pooja.devmateai.presentation.screens.login.LoginScreen
import com.pooja.devmateai.presentation.screens.welcome.WelcomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        composable("welcome") {

            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate("login")
                }
            )

        }

        composable("login") {

            LoginScreen(
                onLoginClick = {

                }
            )

        }

    }
}