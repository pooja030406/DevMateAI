package com.pooja.devmateai.model

data class OpenRouterResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: AssistantMessage
)

data class AssistantMessage(
    val content: String
)