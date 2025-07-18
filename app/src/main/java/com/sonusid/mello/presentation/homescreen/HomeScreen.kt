// In file: app/src/main/java/com/sonusid/mello/presentation/homescreen/HomeScreen.kt

package com.sonusid.mello.presentation.homescreen


import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import com.sonusid.mello.presentation.createpost.CreatePostCard

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: FeedViewModel = hiltViewModel(),
    onCreatePostClick: () -> Unit = {}
) {
    val feed by viewModel.feed.collectAsState()
    var showCreatePostDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) },
            )
        },
        floatingActionButton = {
            ExpandableFab(
                onCreatePost = { showCreatePostDialog = true },
                onInbox = {},
                onSearch = {}
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

        // Show the CreatePostCard as a Dialog
        if (showCreatePostDialog) {
            Dialog(onDismissRequest = { /* Dialog is locked, only dismiss via buttons */ }) { // <--- CHANGED HERE
                CreatePostCard(
                    onPostCreated = { post ->
                        // Handle the created post here (e.g., add to feed ViewModel)
                        println("New Post Created: ${post.content}")
                        // viewModel.addPost(post) // You would uncomment and implement this in FeedViewModel
                        showCreatePostDialog = false // Dismiss dialog after creation
                    },
                    onDismiss = { showCreatePostDialog = false } // Dismiss dialog on Cancel
                )
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
    var showCreatePostDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mello 🐣", style = MaterialTheme.typography.titleLarge) }
            )
        },
        floatingActionButton = {
            ExpandableFab(
                onCreatePost = { showCreatePostDialog = true },
                onInbox = {},
                onSearch = {}
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

        if (showCreatePostDialog) {
            Dialog(onDismissRequest = { /* Dialog is locked in preview too */ }) { // <--- CHANGED HERE
                CreatePostCard(
                    onPostCreated = { /* Preview doesn't add posts */ },
                    onDismiss = { showCreatePostDialog = false }
                )
            }
        }
    }
}