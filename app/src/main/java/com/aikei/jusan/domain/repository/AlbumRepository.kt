package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.AlbumApiService
import com.aikei.jusan.data.model.Album
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val apiService: AlbumApiService
) {

    suspend fun getAlbums(): List<Album> {
        return apiService.getAlbums()
    }
}
