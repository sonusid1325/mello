package com.sonusid.mello.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            Text(post.username, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            MarkdownText(
                markdown = post.content,
                modifier = Modifier.fillMaxWidth()
            )

            post.imageUrl?.let { imageUrl ->
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Post Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                formatDate(post.timestamp),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
