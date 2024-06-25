import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micharlie.healthcare.ui.screens.Community.getCurrentUserId
import com.micharlie.healthcare.ui.theme.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController) {
    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf(listOf<String>()) } // Lista de comentarios
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
                        if (content.isNotEmpty()) {
                            // Add the comment to the list
                            comments = comments + content
                            // Clear the text field
                            content = ""
                            errorMessage = ""
                        } else {
                            errorMessage = "Content cannot be empty"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message ?: "Error creating post"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(contrasPrimaryButtons)
            ) {
                Text("Post", color = white)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar los comentarios
            comments.forEach { comment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(cardsBackgroud)
                ) {
                    Text(
                        text = comment,
                        color = white, // O cambia a color que se vea mejor sobre cardsBackgroud
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

