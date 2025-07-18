package com.sonusid.mello.presentation.createpost

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sonusid.mello.domain.models.Post
import androidx.compose.ui.tooling.preview.Preview // Import for @Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    onPostCreated: (Post) -> Unit
) {
    var content by remember { mutableStateOf(TextFieldValue("")) }
    var codeSnippet by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Post") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val newPost = Post(
                        id = "dummyId_${System.currentTimeMillis()}",
                        username = "CurrentUser",
                        content = content.text,
                        timestamp = System.currentTimeMillis(),
                        codeSnippet = codeSnippet.text.takeIf { it.isNotBlank() },
                        imageUrl = imageUrl.text.takeIf { it.isNotBlank() }
                    )
                    println("Post: $newPost")
                    onPostCreated(newPost)
                }
            ) {
                Text("📤")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("What's on your mind?") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = codeSnippet,
                onValueChange = { codeSnippet = it },
                label = { Text("Code Snippet (optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 10
            )
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL (optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CreatePostScreenPreview() {
    // In a preview, you typically provide a no-op (empty) lambda
    // for callbacks, as you're just visualizing the UI.
    CreatePostScreen(onPostCreated = {})
}