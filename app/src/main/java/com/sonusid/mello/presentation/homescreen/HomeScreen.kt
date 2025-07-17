package com.sonusid.mello.presentation.homescreen


import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sonusid.mello.domain.models.Post
import com.sonusid.mello.presentation.components.ExpandableFab
import com.sonusid.mello.presentation.components.PostItem
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

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Preview(name = "Light Mode", showBackground = true)
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
                Building Mello with Jetpack Compose 😍
            """.trimIndent(),
            codeSnippet = """                
                fun main() {
                    println("Hello, World!")
                }
            """.trimIndent(),
            timestamp = System.currentTimeMillis(),
            imageUrl = "https://spaidy.vercel.app/avatars/premium/spaidy.jpg"
        ),
        Post(
            id = "2",
            username = "ソヌ",
            content = """
                Building Mello with Jetpack Compose 😍
            """.trimIndent(),
            codeSnippet = """
                @Composable
                fun Greeting(name: String) {
                    Text(text = "Hello Compose!")
                }
            """.trimIndent(),
            timestamp = System.currentTimeMillis()
        ),
        Post(
            id = "3",
            username = "hydra_dev",
            content = "Building Mello!!!".trimIndent(),
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
            ExpandableFab(
                onCreatePost = {},
                onInbox = {}
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


