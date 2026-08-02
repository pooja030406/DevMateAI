package com.pooja.devmateai.model

data class GenerationConfig(
    val temperature: Double = 0.0,
    val topP: Double = 1.0,
    val topK: Int = 1
)