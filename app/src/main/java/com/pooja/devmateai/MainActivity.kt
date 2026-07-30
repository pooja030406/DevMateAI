package com.pooja.devmateai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pooja.devmateai.presentation.screens.welcome.WelcomeScreen
import com.pooja.devmateai.ui.theme.DevMateaiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevMateaiTheme {

                WelcomeScreen(
                    onGetStartedClick = {

                    }
                )

            }
        }
    }
}