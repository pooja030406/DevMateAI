package com.pooja.devmateai.presentation.screens.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pooja.devmateai.R

@Composable
fun WelcomeScreen(
    onGetStartedClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_ai),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "DevMate AI",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your Personal AI Interview Coach",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onGetStartedClick,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {

            Text(
                text = "Get Started",
                fontSize = 18.sp
            )

        }

    }
}