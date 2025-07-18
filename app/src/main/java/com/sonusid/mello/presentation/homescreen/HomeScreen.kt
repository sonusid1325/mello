// In file: app/src/main/java/com/sonusid/mello/presentation/homescreen/HomeScreen.kt

package com.sonusid.mello.presentation.homescreen


import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.sonusid.mello.R
import com.sonusid.mello.domain.models.Post
import com.sonusid.mello.presentation.components.ExpandableFab
import com.sonusid.mello.presentation.components.PostItem
import com.sonusid.mello.presentation.createpost.CreatePostCard
import com.sonusid.mello.ui.theme.MelloTheme

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
        imageUrl = "https://spaidy.vercel.app/avatars/premium/sonusid.jpg"
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
        username = "_tea.chaii",
        content = "Building Mello!!!".trimIndent(),
        timestamp = System.currentTimeMillis()
    ),
    Post(
    id = "3",
    username = "_tea.chaii",
    content = "Building Mello!!!".trimIndent(),
    timestamp = System.currentTimeMillis()
)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: FeedViewModel = hiltViewModel()
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
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = padding.calculateBottomPadding() + 80.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mockPosts) { post ->
                    PostItem(post = post)
                }
            }

            // --- Custom Floating Bottom Navigation Bar (67% width and aligned left) ---
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    // --- SYNCHRONIZED: Padding values match HomeScreen ---
                    .padding(bottom = 50.dp, start = 40.dp)
                    // --- SYNCHRONIZED: fillMaxWidth value matches HomeScreen ---
                    .fillMaxWidth(0.70f)
                    .clip(RoundedCornerShape(percent = 50)),
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { println("Home Clicked (Preview)") }) {
                        Icon(painterResource(R.drawable.message_circle), contentDescription = "Home")
                    }
                    IconButton(onClick = { println("Discover Clicked (Preview)") }) {
                        Icon(Icons.Filled.Search, contentDescription = "Discover")
                    }
                    IconButton(onClick = { println("Profile Clicked (Preview)") }) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                }
            }
        }

        if (showCreatePostDialog) {
            Dialog(onDismissRequest = { /* Dialog is locked */ }) {
                CreatePostCard(
                    onPostCreated = { post ->
                        println("New Post Created: ${post.content}")
                        showCreatePostDialog = false
                    },
                    onDismiss = { showCreatePostDialog = false }
                )
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true,
    device = "id:pixel_9_pro", showSystemUi = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
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
                onSearch = {},
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = padding.calculateBottomPadding() + 80.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mockPosts) { post ->
                    PostItem(post = post)
                }
            }

            // --- Custom Floating Bottom Navigation Bar (67% width, aligned left) in Preview ---
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    // --- SYNCHRONIZED: Padding values match HomeScreen ---
                    .padding(bottom = 50.dp, start = 20.dp)
                    // --- SYNCHRONIZED: fillMaxWidth value matches HomeScreen ---
                    .fillMaxWidth(0.70f)
                    .clip(RoundedCornerShape(percent = 50)),
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { println("Home Clicked (Preview)") }) {
                        Icon(painterResource(R.drawable.message_circle), contentDescription = "Home")
                    }
                    IconButton(onClick = { println("Discover Clicked (Preview)") }) {
                        Icon(Icons.Filled.Search, contentDescription = "Discover")
                    }
                    IconButton(onClick = { println("Profile Clicked (Preview)") }) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                }
            }
        }

        if (showCreatePostDialog) {
            Dialog(onDismissRequest = { /* Dialog is locked in preview too */ }) {
                CreatePostCard(
                    onPostCreated = { /* Preview doesn't add posts */ },
                    onDismiss = { showCreatePostDialog = false }
                )
            }
        }
    }
}