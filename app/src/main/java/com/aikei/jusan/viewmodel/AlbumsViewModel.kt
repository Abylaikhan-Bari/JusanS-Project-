package com.aikei.jusan.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.Album
import com.aikei.jusan.data.repository.AlbumRepository

class AlbumsViewModel : ViewModel() {
    private val albumRepository = AlbumRepository()
    val albums: List<Album> = albumRepository.getAlbums()
}
