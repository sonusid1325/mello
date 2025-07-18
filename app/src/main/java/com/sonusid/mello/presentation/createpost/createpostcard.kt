// In file: app/src/main/java/com/sonusid/mello/presentation/createpost/CreatePostCard.kt

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostCard(
    onPostCreated: (Post) -> Unit,
    onDismiss: () -> Unit
) {
    val maxContentLength = 200

    var content by remember { mutableStateOf(TextFieldValue("")) }
    var codeSnippet by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf("") } // URI string

    var showCodeInput by remember { mutableStateOf(false) }

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

    Card(
        modifier = Modifier
            .fillMaxWidth() // <--- CHANGED: Now takes full width
            .fillMaxHeight(0.5f), // <--- CHANGED: Now takes exactly 50% of screen height
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Column inside card fills card
                .padding(16.dp) // Internal padding for card content
        ) {
            // --- Scrollable Content Area ---
            Column(
                modifier = Modifier
                    .weight(1f) // Takes up remaining vertical space
                    .verticalScroll(rememberScrollState()), // Make this part scrollable
                verticalArrangement = Arrangement.spacedBy(12.dp) // Spacing for items within scrollable area
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
                    // maxLines = 10, // Removed as verticalScroll handles content exceeding bounds
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

                // No Spacer here, as verticalArrangement handles it

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
                                .heightIn(min = 80.dp, max = 200.dp) // Maintain min/max height for code input
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
                        Spacer(Modifier.height(8.dp)) // Internal spacer within code section
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
                        }
                    }
                }

                // No Spacer here, as verticalArrangement handles it

                // Display selected image preview and a "Remove Image" button if an image is selected
                if (imageUrl.isNotBlank()) {
                    Column {
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
                        Spacer(Modifier.height(8.dp)) // Internal spacer within image section
                        Button(
                            onClick = {
                                imageUrl = ""
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Remove Image")
                        }
                    }
                }
            } // End of scrollable Column

            // --- Fixed Action Buttons Area (not scrollable) ---
            Spacer(Modifier.height(16.dp)) // Spacer before action chips

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
                        onDismiss()
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
                        onDismiss()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Post")
                }
            }
        }
    }
}

// ActionChip and Preview functions remain the same
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
fun CreatePostCardPreview() {
    CreatePostCard(onPostCreated = {}, onDismiss = {})
}