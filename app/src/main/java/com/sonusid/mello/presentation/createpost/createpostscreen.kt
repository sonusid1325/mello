package com.sonusid.mello.presentation.createpost

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    onPostCreated: () -> Unit
) {
    var content by remember { mutableStateOf(TextFieldValue("")) }
    var codeSnippet by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Post") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Actually send this to backend
                    println("Post: $content\nCode: $codeSnippet")
                    onPostCreated()
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
        }
    }
}
