package com.micharlie.healthcare.ui.screens.Community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.components.BottomBar
import com.micharlie.healthcare.ui.components.DrawerBar
import com.micharlie.healthcare.ui.components.TopBar
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel
import com.micharlie.healthcare.ui.login.SharedPreferencesManager
import com.micharlie.healthcare.ui.navigation.ScreenRoute
import com.micharlie.healthcare.ui.theme.contrasPrimaryButtons
import com.micharlie.healthcare.ui.theme.contrast1
import com.micharlie.healthcare.ui.theme.contrast2
import com.micharlie.healthcare.ui.theme.primary
import com.micharlie.healthcare.ui.theme.tertiary
import com.micharlie.healthcare.ui.theme.white
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreatePostScreen(navController: NavController,sessionState: Boolean = true, sharedPreferencesManager: SharedPreferencesManager, getVideoViewModel: GetVideoViewModel) {

    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val authorId = getCurrentUserId() // Obtén el ID del usuario actual

    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val token = sharedPreferencesManager.getToken()
    // Lista mutable para almacenar los comentarios
    val comments = remember { mutableStateListOf<String>() }
    if (token != null) {
        LaunchedEffect(key1 = token) {
            while (true) {
                getVideoViewModel.getUsersData(token)
                delay(5000) // Actualiza cada 5 segundos
            }
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerBar(drawerState = drawerState, sessionState = remember { mutableStateOf(true) } , content = {
            Scaffold(
                bottomBar = { BottomBar() },
                topBar = { TopBar(drawerState = drawerState) }
            ) {
                Surface (color = primary) {
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
                                        getVideoViewModel.postComment(token, content)
                                        println("token: $token")
                                        println("Post created successfully with comment: $comment")
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
                        CommentsList(comments = comments)
                    }
                }
            }

    }, navController = navController, getVideoViewModel , sharedPreferencesManager)
}
