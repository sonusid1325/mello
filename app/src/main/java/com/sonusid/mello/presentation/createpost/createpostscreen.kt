package com.sonusid.mello.presentation.createpost

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    onPostCreated: (Post) -> Unit
) {
    val maxContentLength = 200

    var content by remember { mutableStateOf(TextFieldValue("")) }
    var codeSnippet by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf("") } // URI string

    var showCodeInput by remember { mutableStateOf(false) } // Controls visibility of BasicTextField for code

    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            println("Selected URI: $uri")
            imageUrl = uri.toString()
        } else {
            println("No media selected")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Post") }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth() // <--- Reverted to full width
                    .fillMaxHeight(0.5f), // <--- CHANGED HERE: Makes the card take 50% of parent height
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize() // Make column fill card
                        .padding(16.dp)
                ) {
                    // Main content/caption input
                    OutlinedTextField(
                        value = content,
                        onValueChange = { newValue ->
                            if (newValue.text.length <= maxContentLength) {
                                content = newValue
                            }
                        },
                        label = { Text("What's on your mind?") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 10,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            errorContainerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        supportingText = {
                            Text(
                                text = "${content.text.length} / $maxContentLength",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                                color = if (content.text.length == maxContentLength) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        }
                    )

                    Spacer(Modifier.height(12.dp))

                    // Optional Code Snippet Input (Stylized BasicTextField)
                    if (showCodeInput) {
                        Column {
                            BasicTextField(
                                value = codeSnippet,
                                onValueChange = { codeSnippet = it },
                                textStyle = TextStyle(
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 80.dp, max = 200.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                                    .padding(12.dp),
                                singleLine = false,
                                decorationBox = { innerTextField ->
                                    if (codeSnippet.text.isEmpty()) {
                                        Text(
                                            "Paste your code snippet here...",
                                            style = TextStyle(
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                            )
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                            Spacer(Modifier.height(8.dp))
                            // Clear Code Button
                            if (codeSnippet.text.isNotBlank()) {
                                Button(
                                    onClick = {
                                        codeSnippet = TextFieldValue("")
                                        showCodeInput = false
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                                ) {
                                    Text("Remove Code")
                                }
                                Spacer(Modifier.height(8.dp))
                            }
                        }
                    }

                    // Display selected image preview and a "Remove Image" button if an image is selected
                    if (imageUrl.isNotBlank()) {
                        Spacer(Modifier.height(8.dp))
                        AsyncImage(
                            model = imageUrl.toUri(),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .align(Alignment.CenterHorizontally),
                        )
                        Spacer(Modifier.height(8.dp))
                        // Remove Image Button
                        Button(
                            onClick = {
                                imageUrl = ""
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Remove Image")
                        }
                        Spacer(Modifier.height(8.dp))
                    }

                    // Spacer to push action chips and buttons to the bottom
                    Spacer(Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ActionChip(
                            text = if (showCodeInput) "Hide Code" else "Add Code",
                            icon = Icons.Default.Code,
                            onClick = {
                                showCodeInput = !showCodeInput
                                if (!showCodeInput) codeSnippet = TextFieldValue("")
                            }
                        )
                        ActionChip(
                            text = if (imageUrl.isNotBlank()) "Change Image" else "Add Image",
                            icon = Icons.Default.AddAPhoto,
                            onClick = {
                                pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Post and Cancel Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                onPostCreated(Post(id = "", content = ""))
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                val newPost = Post(
                                    id = "dummyId_${System.currentTimeMillis()}",
                                    username = "CurrentUser",
                                    content = content.text,
                                    timestamp = System.currentTimeMillis(),
                                    codeSnippet = codeSnippet.text.takeIf { it.isNotBlank() && showCodeInput },
                                    imageUrl = imageUrl.takeIf { it.isNotBlank() }
                                )
                                println("Post: $newPost")
                                onPostCreated(newPost)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Post")
                        }
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

@Preview(showBackground = true, device = "id:pixel_9_pro", showSystemUi = true)
@Composable
fun CreatePostScreenNewDesignPreview() {
    CreatePostScreen(onPostCreated = {})
}