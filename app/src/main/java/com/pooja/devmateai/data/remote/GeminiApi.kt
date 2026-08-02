package com.pooja.devmateai.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import com.pooja.devmateai.model.GeminiRequest
import com.pooja.devmateai.model.GeminiResponse

interface GeminiApi {

    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun analyzeResume(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): Response<GeminiResponse>

}