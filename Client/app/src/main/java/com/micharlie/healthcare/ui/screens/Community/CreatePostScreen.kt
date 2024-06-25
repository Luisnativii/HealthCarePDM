package com.micharlie.healthcare.ui.screens.Community

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micharlie.healthcare.data.api.CommentApi
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoState
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController, sharedPreferencesManager: SharedPreferencesManager, getVideoViewModel: GetVideoViewModel) {
    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val authorId = getCurrentUserId() // Obtén el ID del usuario actual

    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val token = sharedPreferencesManager.getToken()


    var comments by remember { mutableStateOf(listOf<CommentApi>()) }


    val state by getVideoViewModel.getVideoState.collectAsState()

    val email = sharedPreferencesManager.getEmail()
    val isAdmin = email == "admin@gmail.com" || email == "root@gmail.com"

    LaunchedEffect(key1 = true) {
        getVideoViewModel.getComments()
    }


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
                    println("asdasd")
                    println("token: $token")
                    println("coment$content")
                    try {
                        val token = sharedPreferencesManager.getToken()
                        val comment = content
                        if (token != null) {

                            println("token: $token")
                            println("Post created successfully with comment: $comment")
                            getVideoViewModel.postComment(token, comment)
                            getVideoViewModel.getComments()



                            println("email: $email")






                        } else {
                            println("Error: Token is null")
                        }

                    } catch (e: Exception) {
                        errorMessage = e.message ?: "Error creating post"
                        println("token: $token")

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text("Post", color = white)
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Mostrar la lista de comentarios

            val currentState = state
            when (currentState) {
                is GetVideoState.CommentsSuccess -> {

                    if (isAdmin) {
                        println("comments: ${currentState.comments}")
                        println("ADMIN")

                        CommentsList(comments = currentState.comments, isAdmin = isAdmin, getVideoViewModel = getVideoViewModel, onDelete = { comment ->
                            // Lógica para eliminar el comentario
                            println("Deleting comment with ID: ${comment.id}")
                            // Aquí puedes llamar a tu ViewModel para eliminar el comentario
                        })

                    }else {
                        CommentsList(comments = currentState.comments, isAdmin = isAdmin, getVideoViewModel = getVideoViewModel, onDelete = { comment ->
                            // Lógica para eliminar el comentario
                            println("Deleting comment with ID: ${comment.id}")
                            // Aquí puedes llamar a tu ViewModel para eliminar el comentario
                        })
                    }
                }
                // Manejar otros estados si es necesario
                else -> {}
            }







        }
    }
}
