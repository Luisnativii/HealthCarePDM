package com.micharlie.healthcare.ui.screens.Community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrasPrimaryButtons
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val authorId = getCurrentUserId() // Obtén el ID del usuario actual

    Surface(color = primary) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("What's on your mind?", color = white) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedTextColor = white,
                    focusedTextColor = white,
                    cursorColor = contrast1,
                    focusedIndicatorColor = contrast2,
                    unfocusedIndicatorColor = tertiary
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    try {
                        // Navigate to the main screen after creating the post
                        navController.navigate(ScreenRoute.HomeSession.route)
                    } catch (e: Exception) {
                        errorMessage = e.message ?: "Error creating post"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(contrasPrimaryButtons),
            ) {
                Text("Post", color = white)
            }
        }
    }
}
