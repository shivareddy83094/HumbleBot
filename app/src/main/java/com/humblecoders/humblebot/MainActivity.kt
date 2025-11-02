package com.humblecoders.humblebot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.humblecoders.humblebot.ui.ChatScreen
import com.humblecoders.humblebot.ui.theme.HumbleBotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HumbleBotTheme {
                ChatScreen()
            }
        }
    }
}

