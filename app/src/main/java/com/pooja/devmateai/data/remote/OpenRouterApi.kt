package com.pooja.devmateai.data.remote

import com.pooja.devmateai.model.OpenRouterRequest
import com.pooja.devmateai.model.OpenRouterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenRouterApi {

    @POST("v1/chat/completions")
    suspend fun analyzeResume(
        @Header("Authorization") authorization: String,
        @Header("HTTP-Referer") referer: String = "https://devmate.ai",
        @Header("X-Title") title: String = "DevMate AI",
        @Body request: OpenRouterRequest
    ): Response<OpenRouterResponse>
}