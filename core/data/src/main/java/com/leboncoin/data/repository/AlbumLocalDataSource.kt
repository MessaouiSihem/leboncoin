package com.leboncoin.data.repository

import com.leboncoin.domain.models.Album

internal interface AlbumLocalDataSource {

    suspend fun fetchLocalAlbums(): List<Album>
    suspend fun saveLocalAlbums(albumList: List<Album>)
}