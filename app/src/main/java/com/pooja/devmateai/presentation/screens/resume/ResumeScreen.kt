package com.pooja.devmateai.presentation.screens.resume

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pooja.devmateai.presentation.viewmodel.ResumeViewModel
import com.pooja.devmateai.utils.PdfUtils
import com.pooja.devmateai.utils.getFileName

@Composable
fun ResumeScreen() {

    var selectedResume by remember {
        mutableStateOf<Uri?>(null)
    }

    var resumeText by remember {
        mutableStateOf("")
    }

    var jobDescription by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current

    val viewModel: ResumeViewModel = viewModel()

    val aiResult by viewModel.result.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val atsScore by viewModel.atsScore.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        selectedResume = uri

        uri?.let {
            resumeText = PdfUtils.extractText(context, it)
        }
    }
    val cardColor = when {
        atsScore >= 75 -> Color(0xFFE8F5E9)   // Green
        atsScore >= 50 -> Color(0xFFFFF3E0)   // Orange
        atsScore > 0 -> Color(0xFFFFEBEE)     // Red
        else -> Color.White
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Resume Analyzer",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Upload your resume and compare it with a Job Description."
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                launcher.launch(arrayOf("application/pdf"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Resume")
        }

        Spacer(modifier = Modifier.height(24.dp))

        selectedResume?.let { uri ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "📄 Selected Resume",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(getFileName(context, uri))

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Characters Extracted: ${resumeText.length}")

                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "💼 Job Description (Optional)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = jobDescription,
                onValueChange = {
                    jobDescription = it
                },
                label = {
                    Text("Paste Job Description")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                enabled = !isLoading,
                onClick = {
                    viewModel.analyzeResume(
                        resumeText = resumeText,
                        jobDescription = jobDescription
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                if (isLoading) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text("Analyzing...")

                } else {

                    Text("Analyze Resume")

                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            if (aiResult.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor
                    )
                ){

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        val cardColor = when {
                            atsScore >= 80 -> Color(0xFFDFF5E1)   // Green
                            atsScore >= 60 -> Color(0xFFFFF3CD)   // Yellow
                            else -> Color(0xFFF8D7DA)             // Red
                        }

                        Text(
                            text = "🤖 AI Feedback",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "ATS Variable = $atsScore",
                            color = Color.Red
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = aiResult
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                clipboard.setText(
                                    AnnotatedString(aiResult)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Copy Feedback")
                        }

                    }

                }

            }

        }

    }

}