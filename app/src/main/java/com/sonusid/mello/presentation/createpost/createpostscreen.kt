package com.sonusid.mello.presentation.createpost

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sonusid.mello.domain.models.Post
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    onPostCreated: (Post) -> Unit
) {
    var content by remember { mutableStateOf(TextFieldValue("")) }
    var codeSnippet by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }

    // States to control visibility of optional input fields
    var showCodeInput by remember { mutableStateOf(false) }
    var showImageInput by remember { mutableStateOf(false) }

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
                        id = "dummyId_${System.currentTimeMillis()}", // Placeholder ID
                        username = "CurrentUser", // Placeholder username
                        content = content.text,
                        timestamp = System.currentTimeMillis(),
                        codeSnippet = codeSnippet.text.takeIf { it.isNotBlank() && showCodeInput }, // Only include if input visible and not blank
                        imageUrl = imageUrl.text.takeIf { it.isNotBlank() && showImageInput } // Only include if input visible and not blank
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Main content/caption input
                    OutlinedTextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text("What's on your mind?") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 10,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent
                        )
                    )

                    Spacer(Modifier.height(12.dp))

                    // Optional Code Snippet Input
                    if (showCodeInput) {
                        OutlinedTextField(
                            value = codeSnippet,
                            onValueChange = { codeSnippet = it },
                            label = { Text("Code Snippet") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = false,
                            maxLines = 10
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    // Optional Image URL Input
                    if (showImageInput) {
                        OutlinedTextField(
                            value = imageUrl,
                            onValueChange = { imageUrl = it },
                            label = { Text("Image URL") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    // Buttons to toggle code and image input visibility
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ActionChip(
                            text = "Add Code",
                            icon = Icons.Default.Code,
                            onClick = { showCodeInput = !showCodeInput }
                        )
                        ActionChip(
                            text = "Add Image",
                            icon = Icons.Default.AddAPhoto,
                            onClick = { showImageInput = !showImageInput }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActionChip(text: String, icon: ImageVector, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(text) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun CreatePostScreenNewDesignPreview() {
    CreatePostScreen(onPostCreated = {})
}