package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Album
import retrofit2.http.GET

interface AlbumApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}
