package com.sonusid.mello.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.sonusid.mello.domain.models.Post
import dev.jeziellago.compose.markdowntext.MarkdownText
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(post.username,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(4.dp))

            MarkdownText(
                markdown = post.content,
                modifier = Modifier.fillMaxWidth(),
                // Regular text style
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                ),
                // Syntax highlight block background & border
                syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
                syntaxHighlightTextColor = MaterialTheme.colorScheme.primary,
                // Color for the dashed line under headings
                headingBreakColor = MaterialTheme.colorScheme.onSurfaceVariant,
                // Set link colors
                linkColor = MaterialTheme.colorScheme.primary,
                enableUnderlineForLink = true, // underline links
                truncateOnTextOverflow = false,
                isTextSelectable = true
            )

            post.imageUrl?.let { url ->
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = url,
                        contentDescription = "Post Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(Modifier.fillMaxSize()) {
                                LottieLoader(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        },
                        error = {
                            Box(Modifier.fillMaxSize()) {
                                LottieLoader(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    )
                }
            }


            Spacer(Modifier.height(8.dp))

            Text(formatDate(post.timestamp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}


fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
