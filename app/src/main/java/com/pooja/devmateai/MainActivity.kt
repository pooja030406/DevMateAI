package com.pooja.devmateai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.pooja.devmateai.presentation.navigation.AppNavigation
import com.pooja.devmateai.ui.theme.DevMateaiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevMateaiTheme {

                DevMateaiTheme {
                    AppNavigation()
                }

            }
        }
    }
}