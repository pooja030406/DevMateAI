package com.pooja.devmateai.data.repository

import com.pooja.devmateai.BuildConfig
import com.pooja.devmateai.data.remote.RetrofitClient
import com.pooja.devmateai.model.Message
import com.pooja.devmateai.model.OpenRouterRequest

class ResumeRepository {

    suspend fun analyzeResume(
        resumeText: String
    ): String {

        val prompt = """
Analyze the following resume.

Give:
1. Overall Score out of 100
2. ATS Score
3. Strengths
4. Weaknesses
5. Suggestions for improvement

Resume:
$resumeText
        """.trimIndent()

        val request = OpenRouterRequest(
            model = "meta-llama/llama-3.3-70b-instruct:free",
            messages = listOf(
                Message(
                    role = "user",
                    content = prompt
                )
            ),
            temperature = 0.0
        )

        val response = RetrofitClient.api.analyzeResume(
            authorization = "Bearer ${BuildConfig.OPENROUTER_API_KEY}",
            request = request
        )

        return if (response.isSuccessful) {

            response.body()
                ?.choices
                ?.firstOrNull()
                ?.message
                ?.content
                ?: "No response"

        } else {

            response.errorBody()?.string()
                ?: "Error ${response.code()}"

        }
    }
}