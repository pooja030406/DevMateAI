package com.pooja.devmateai.presentation.screens.interview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pooja.devmateai.presentation.components.DropdownSelector
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun InterviewSetupScreen(
    onStartInterview: () -> Unit
) {
    var selectedCompany by remember { mutableStateOf("Amazon") }

    val companies = listOf(
        "Amazon",
        "Google",
        "Microsoft",
        "Adobe",
        "Flipkart",
        "Generic"
    )

    var selectedInterviewType by remember {
        mutableStateOf("Technical")
    }

    val interviewTypes = listOf(
        "HR",
        "Technical",
        "Mixed"
    )

    var selectedDifficulty by remember {
        mutableStateOf("Medium")
    }

    val difficulties = listOf(
        "Easy",
        "Medium",
        "Hard"
    )
    var selectedDuration by remember {
        mutableStateOf("30 Minutes")
    }

    val durations = listOf(
        "15 Minutes",
        "30 Minutes",
        "45 Minutes",
        "60 Minutes"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "🎤 AI Mock Interview",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Prepare for your next interview",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        DropdownSelector(
            title = "Company",
            selectedItem = selectedCompany,
            items = companies,
            onItemSelected = {
                selectedCompany = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownSelector(
            title = "Interview Type",
            selectedItem = selectedInterviewType,
            items = interviewTypes,
            onItemSelected = {
                selectedInterviewType = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownSelector(
            title = "Difficulty",
            selectedItem = selectedDifficulty,
            items = difficulties,
            onItemSelected = {
                selectedDifficulty = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropdownSelector(
            title = "Duration",
            selectedItem = selectedDuration,
            items = durations,
            onItemSelected = {
                selectedDuration = it
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                onStartInterview()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4)
            )
        ) {
            Text(
                text = "Start Interview",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

    }
}