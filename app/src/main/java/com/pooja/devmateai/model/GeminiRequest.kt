package com.pooja.devmateai.model

import com.pooja.devmateai.data.remote.Content

data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig =
        GenerationConfig(
            temperature = 0.0,
            topP = 0.1,
            topK = 1
        )
)