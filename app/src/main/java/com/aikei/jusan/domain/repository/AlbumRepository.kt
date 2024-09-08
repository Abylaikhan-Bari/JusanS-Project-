package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.model.Album
import javax.inject.Inject

class AlbumRepository @Inject constructor() {

    fun getAlbums(): List<Album> {
        // Simulate data fetching, replace with actual data source logic
        return listOf(
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("1", "Album Title 1", "User 1", 1624032000, "url_to_cover1"),
            Album("2", "Album Title 2", "User 2", 1624035600, "url_to_cover2")
        )
    }
}
