package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.PhotoApiService
import com.aikei.jusan.data.model.Photo
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiService: PhotoApiService
) {
    suspend fun getPhotosByAlbumId(albumId: Int): List<Photo> {
        return apiService.getPhotosByAlbumId(albumId)
    }

    // Add this method to fetch all photos
    suspend fun getAllPhotos(): List<Photo> {
        return apiService.getAllPhotos() // Assuming the API provides an endpoint for fetching all photos
    }
}
