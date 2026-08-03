package com.pooja.devmateai.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pooja.devmateai.presentation.screens.login.LoginScreen
import com.pooja.devmateai.presentation.screens.welcome.WelcomeScreen
import com.pooja.devmateai.presentation.screens.home.HomeScreen
import com.pooja.devmateai.presentation.screens.resume.ResumeScreen
import com.pooja.devmateai.presentation.screens.interview.InterviewSetupScreen
import com.pooja.devmateai.presentation.screens.interview.InterviewScreen
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
                    navController.navigate("home")
                }
            )

        }
        composable("home") {
            HomeScreen(
                onResumeClick = {
                    navController.navigate("resume")
                },
                onInterviewClick = {
                    navController.navigate("interview")
                }
            )
        }
        composable("resume") {
            ResumeScreen()
        }

        composable("interview") {
            InterviewSetupScreen(
                onStartInterview = {
                    navController.navigate("interviewChat")
                }
            )
        }
        composable("interviewChat") {
            InterviewScreen()
        }
    }
}