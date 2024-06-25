package com.micharlie.healthcare.ui.screens.Community
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AdminScreen(navController: NavController) {
    val pendingPosts = remember { mutableStateListOf<Post>() }

    // Cargar publicaciones pendientes
    LaunchedEffect(Unit) {
        pendingPosts.clear()
        pendingPosts.addAll(getPendingPosts())
    }

    LazyColumn {
        items(pendingPosts) { post ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(post.content, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        approvePost(post.id)
                        pendingPosts.remove(post)
                    }) {
                        Text("Approve")
                    }
                    Button(onClick = {
                        deletePost(post.id, getCurrentUserId())
                        pendingPosts.remove(post)
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
