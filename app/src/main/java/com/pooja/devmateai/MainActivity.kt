package com.pooja.devmateai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pooja.devmateai.ui.theme.DevMateaiTheme
import com.pooja.devmateai.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevMateaiTheme {

                AppNavigation()

            }
        }
    }
}