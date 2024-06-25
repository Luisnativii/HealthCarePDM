package com.micharlie.healthcare.ui.screens.Community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.micharlie.healthcare.data.api.CommentApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel


@Composable
fun AdminCard(comment: CommentApi, getVideoViewModel: GetVideoViewModel) {



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${comment.id}")
            Text(text = "User Name: ${comment.userName}")
            Text(text = "Content: ${comment.content}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    println("Delete comment with id: ${comment.id}")
                    getVideoViewModel.deleteVideo(comment.id ?: "defaultId")
                    getVideoViewModel.getComments()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Delete")
            }
        }
    }
}