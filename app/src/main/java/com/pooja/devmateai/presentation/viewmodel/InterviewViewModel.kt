package com.pooja.devmateai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pooja.devmateai.data.repository.InterviewRepository
import com.pooja.devmateai.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InterviewViewModel : ViewModel() {

    private val repository = InterviewRepository()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {

        _messages.value = listOf(

            ChatMessage(
                text = """
Hi Pooja 👋

Welcome to your AI Mock Interview.

Let's begin!

Tell me about yourself.
                """.trimIndent(),
                isUser = false
            )

        )

    }

    fun sendMessage(answer: String) {

        if (answer.isBlank()) return

        viewModelScope.launch {

            _isLoading.value = true

            // Add user message
            _messages.value = _messages.value + ChatMessage(
                text = answer,
                isUser = true
            )

            // Create conversation prompt
            val conversation = buildString {

                append(
                    """
You are an experienced technical interviewer.

Continue this interview naturally.

Do not restart the interview.

Ask only ONE next interview question.

Conversation:

                    """.trimIndent()
                )

                _messages.value.forEach {

                    if (it.isUser)
                        append("\nCandidate: ${it.text}")

                    else
                        append("\nInterviewer: ${it.text}")

                }

            }

            val aiReply = repository.askInterviewQuestion(conversation)

            _messages.value = _messages.value + ChatMessage(
                text = aiReply,
                isUser = false
            )

            _isLoading.value = false

        }

    }

}