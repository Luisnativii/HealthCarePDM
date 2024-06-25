package com.micharlie.healthcare.ui.screens.Community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.micharlie.healthcare.data.api.CommentApi
import com.micharlie.healthcare.ui.components.ViewModel.GetVideoViewModel

@Composable
fun CommentsList(comments: List<CommentApi>, isAdmin: Boolean, getVideoViewModel: GetVideoViewModel, onDelete: (CommentApi) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(comments) { comment ->
            if (isAdmin) {
                AdminCard(comment = comment, getVideoViewModel = getVideoViewModel)
            } else {
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
                    }
                }
            }
        }
    }
}
