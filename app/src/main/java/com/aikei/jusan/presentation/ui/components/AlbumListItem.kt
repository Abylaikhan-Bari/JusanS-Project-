package com.aikei.jusan.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aikei.jusan.data.model.Album

@Composable
fun AlbumListItem(album: Album) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Replace with your actual image loading logic for the album
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Placeholder image from Android system
                contentDescription = "Placeholder Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
            )

            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Title", style = MaterialTheme.typography.titleMedium)
                Text(text = "Username", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}