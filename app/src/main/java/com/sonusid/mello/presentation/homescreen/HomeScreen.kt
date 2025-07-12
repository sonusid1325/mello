package com.sonusid.mello.presentation.homescreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) }
            )
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

@Preview(showBackground = true)
@Preview(name = "Dark Mode", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, showBackground = true)
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
        Post(id = "1", username = "ソヌ", content = "Yo I'm building Mello with Jetpack Compose ✨", timestamp = System.currentTimeMillis()),
        Post(id = "2", username = "hydra_dev", content = "Just dropped a Bun shell into a CTF room 💀", timestamp = System.currentTimeMillis())
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) }
            )
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

