package com.sonusid.mello.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Navigation
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sonusid.mello.R


@Composable
fun ExpandableFab(
    onCreatePost: () -> Unit,
    onInbox: () -> Unit,
    onSearch: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 45f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "FAB Rotation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End
        ) {
            // Edit FAB
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(tween(150)) + slideInVertically(
                    initialOffsetY = { it / 2 },
                    animationSpec = tween(150)
                ),
                exit = fadeOut(tween(150)) + slideOutVertically(
                    targetOffsetY = { it / 2 },
                    animationSpec = tween(150)
                )
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        expanded = false
                        onSearch()
                    },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ) {
                    Icon(painterResource(R.drawable.user_round_pen), contentDescription = "Inbox")
                }
            }

            // Create Post FAB
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(tween(150)) + slideInVertically(initialOffsetY = {
                        it
                    }, animationSpec = tween( 200)),
                exit = fadeOut(tween(150)) + slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(200)
                )
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        expanded = false
                        onCreatePost()
                    },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ) {
                    Icon(Icons.Rounded.Edit, contentDescription = "Create Post")
                }
            }

            // Main FAB
            FloatingActionButton(
                onClick = { expanded = !expanded },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Rounded.Navigation,
                    contentDescription = if (expanded) "Close Menu" else "Open Menu",
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
    }
}

