package com.pooja.devmateai.model

data class OpenRouterRequest(
    val model: String,
    val messages: List<Message>,
    val temperature: Double = 0.0
)

data class Message(
    val role: String,
    val content: String
)