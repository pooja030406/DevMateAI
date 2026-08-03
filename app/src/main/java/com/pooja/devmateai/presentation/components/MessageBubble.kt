package com.pooja.devmateai.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pooja.devmateai.model.ChatMessage

@Composable
fun MessageBubble(
    message: ChatMessage
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            if (message.isUser)
                Arrangement.End
            else
                Arrangement.Start
    ) {

        Surface(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth(0.8f),

            shape = RoundedCornerShape(18.dp),

            color =
                if (message.isUser)
                    Color(0xFF6750A4)
                else
                    Color(0xFFECECEC)

        ) {

            Column(
                modifier = Modifier.padding(14.dp)
            ) {

                Text(
                    text =
                        if (message.isUser)
                            "👤 You"
                        else
                            "🤖 DevMate AI",

                    fontWeight = FontWeight.Bold,

                    color =
                        if (message.isUser)
                            Color.White
                        else
                            Color.Black
                )

                Text(
                    text = message.text,
                    modifier = Modifier.padding(top = 8.dp),

                    color =
                        if (message.isUser)
                            Color.White
                        else
                            Color.Black,

                    style = MaterialTheme.typography.bodyLarge
                )

            }

        }

    }

}