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
