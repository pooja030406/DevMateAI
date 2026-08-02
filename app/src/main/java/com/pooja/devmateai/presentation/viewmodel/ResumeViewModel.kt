package com.pooja.devmateai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pooja.devmateai.BuildConfig
import com.pooja.devmateai.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.pooja.devmateai.model.OpenRouterRequest
import com.pooja.devmateai.model.Message

class ResumeViewModel : ViewModel() {

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private val _atsScore = MutableStateFlow(0)
    val atsScore: StateFlow<Int> = _atsScore

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun analyzeResume(
        resumeText: String,
        jobDescription: String
    ) {

        viewModelScope.launch {

            _isLoading.value = true

            try {

                val prompt = """
You are an expert ATS Resume Reviewer.

Analyze the following resume.

Resume:
$resumeText

Job Description:
$jobDescription

Give the response in EXACTLY this format:

ATS Score: XX/100

Overall Score: XX/100

Strengths:
- ...

Weaknesses:
- ...

Missing Keywords:
- ...

Suggestions:
- ...

Improved Resume Summary:
...
""".trimIndent()

                val request = OpenRouterRequest(
                    model = "openai/gpt-oss-20b:free",
                    messages = listOf(
                        Message(
                            role = "user",
                            content = prompt
                        )
                    ),
                    temperature = 0.0
                )

                // ===========================
                // TEMP DEBUG
                // ===========================
                val response = RetrofitClient.api.analyzeResume(
                    authorization = "Bearer ${BuildConfig.OPENROUTER_API_KEY}",
                    request = request
                )
                if (response.isSuccessful) {

                    _result.value =
                        response.body()
                            ?.choices
                            ?.firstOrNull()
                            ?.message
                            ?.content
                            ?.replace("**", "")
                            ?: "No response"

                    val regex = Regex("""ATS Score:\s*(\d+)""")
                    val match = regex.find(_result.value)

                    _atsScore.value =
                        match?.groupValues?.get(1)?.toIntOrNull() ?: 0

                } else {

                    _result.value = """
Error Code: ${response.code()}

${response.errorBody()?.string()}
""".trimIndent()

                }
            } catch (e: Exception) {

                _result.value = e.stackTraceToString()

            } finally {

                _isLoading.value = false

            }

        }

    }
}