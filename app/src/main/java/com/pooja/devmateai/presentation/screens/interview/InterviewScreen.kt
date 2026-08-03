package com.pooja.devmateai.presentation.screens.interview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pooja.devmateai.presentation.components.MessageBubble
import com.pooja.devmateai.presentation.viewmodel.InterviewViewModel

@Composable
fun InterviewScreen() {

    val viewModel: InterviewViewModel = viewModel()

    val messages by viewModel.messages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var answer by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "🎤 AI Mock Interview",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(messages) { message ->

                MessageBubble(message)

            }

        }

        if (isLoading) {

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = answer,
                onValueChange = {
                    answer = it
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Type your answer...")
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {

                    if (answer.isNotBlank()) {

                        viewModel.sendMessage(answer)

                        answer = ""

                    }

                }
            ) {

                Text("Send")

            }

        }

    }

}