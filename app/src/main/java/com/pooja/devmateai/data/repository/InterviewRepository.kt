package com.pooja.devmateai.data.repository

import com.pooja.devmateai.BuildConfig
import com.pooja.devmateai.data.remote.RetrofitClient
import com.pooja.devmateai.model.Message
import com.pooja.devmateai.model.OpenRouterRequest

class InterviewRepository {

    suspend fun askInterviewQuestion(
        prompt: String
    ): String {

        val request = OpenRouterRequest(
            model = "openai/gpt-oss-20b:free",
            messages = listOf(
                Message(
                    role = "system",
                    content = """
You are an experienced Software Engineering interviewer.

Conduct a realistic interview.

Rules:
- Ask only ONE question at a time.
- Wait for the candidate's answer.
- Continue naturally.
- Do not restart the interview.
- If the answer is weak, ask follow-up questions.
- If the answer is good, move to the next question.
- Keep the interview professional.
Formatting Rules:
- Respond only in plain text.
- Do not use Markdown.
- Do not use **bold**.
- Do not use bullet points unless necessary.
- Keep responses conversational like a real interviewer.
""".trimIndent()
                ),
                Message(
                    role = "user",
                    content = prompt
                )
            ),
            temperature = 0.4
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