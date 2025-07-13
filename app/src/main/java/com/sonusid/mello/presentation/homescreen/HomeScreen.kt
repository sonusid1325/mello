package com.sonusid.mello.presentation.homescreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sonusid.mello.presentation.components.PostItem
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sonusid.mello.domain.models.Post
import com.sonusid.mello.ui.theme.MelloTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val feed by viewModel.feed.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open Create Post Screen */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text("+") // or use an icon like Icons.Default.Add
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(feed) { post ->
                PostItem(post = post)
            }
        }
    }
}


@Preview(name = "Dark Mode", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light Mode", showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MelloTheme {
        Surface {
            PreviewHomeScreenContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewHomeScreenContent() {
    val mockPosts = listOf(
        Post(
            id = "1",
            username = "ソヌ",
            content = """
                Building **Mello** with Jetpack Compose 😍

                ```kotlin
                @Composable
                fun HelloMello() {
                    Text("Hello from ソヌ ✨")
                }
                ```
            """.trimIndent(),
            timestamp = System.currentTimeMillis(),
            imageUrl = "https://spaidy.vercel.app/avatars/premium/spaidy.jpg"
        ),
        Post(
            id = "2",
            username = "ソヌ",
            content = """
                > So you know that you can post like a pro dev. using markdown in Mello.
                ```
            """.trimIndent(),
            timestamp = System.currentTimeMillis()
        ),
        Post(
            id = "3",
            username = "hydra_dev",
            content = """
                Dropped a shell in Bun 🐢

                ```bash
                bun run hydra-os.ts
                ```
            """.trimIndent(),
            timestamp = System.currentTimeMillis()
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open Create Post Screen */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Create Post") // or use an icon like Icons.Default.Add
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mockPosts) { post ->
                PostItem(post = post)
            }
        }
    }
}

