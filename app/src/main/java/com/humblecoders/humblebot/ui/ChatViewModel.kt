package com.humblecoders.humblebot.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.humblecoders.humblebot.model.ChatMessage
import com.humblecoders.humblebot.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val repository = ChatRepository()

    val messages = mutableStateListOf<ChatMessage>()
    var isLoading = false
        private set

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        // Add user message
        messages.add(ChatMessage(text = text, isFromUser = true))

        // Show loading
        isLoading = true

        // Send to API
        viewModelScope.launch {
            val result = repository.sendMessage(text)

            result.onSuccess { response ->
                messages.add(ChatMessage(text = response, isFromUser = false))
            }.onFailure { error ->
                messages.add(ChatMessage(
                    text = "Error: ${error.message ?: "Something went wrong"}",
                    isFromUser = false
                ))
            }

            isLoading = false
        }
    }

    fun clearChat() {
        messages.clear()
    }
}
