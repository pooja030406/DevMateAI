package com.pooja.devmateai.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pooja.devmateai.presentation.components.FeatureCard

@Composable
fun HomeScreen(
    onResumeClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "👋 Hi Pooja",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ready to crack your next interview?",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        FeatureCard(
            title = "📄 Resume Analyzer",
            onClick = {
                onResumeClick()
            }
        )

        FeatureCard(
            title = "🎤 AI Mock Interview",
            onClick = {}
        )

        FeatureCard(
            title = "💻 DSA Practice",
            onClick = {}
        )

        FeatureCard(
            title = "🤖 Ask DevMate AI",
            onClick = {}
        )

        FeatureCard(
            title = "📊 Interview History",
            onClick = {}
        )

    }
}

