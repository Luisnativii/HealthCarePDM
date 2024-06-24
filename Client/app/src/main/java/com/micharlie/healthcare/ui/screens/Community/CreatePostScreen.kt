package com.micharlie.healthcare.ui.screens.Community

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.navigation.ScreenRoute

@Composable
fun CreatePostScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val authorId = getCurrentUserId() // Obtén el ID del usuario actual

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
            label = { Text("What's on your mind?") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = {
                try {
                    val newPost = createPost(authorId, content)
                    // Navega a la pantalla principal después de crear la publicación
                    navController.navigate(ScreenRoute.HomeSession.route)
                } catch (e: Exception) {
                    errorMessage = e.message ?: "Error creating post"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Post")
        }
    }
}
