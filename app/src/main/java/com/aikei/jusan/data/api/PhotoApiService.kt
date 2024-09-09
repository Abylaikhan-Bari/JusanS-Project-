package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("/photos")
    suspend fun getPhotosByAlbumId(@Query("albumId") albumId: Int): List<Photo>

    // Add this endpoint to fetch all photos
    @GET("/photos")
    suspend fun getAllPhotos(): List<Photo>
}
