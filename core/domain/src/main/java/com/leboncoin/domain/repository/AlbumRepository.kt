package com.leboncoin.domain.repository

import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album

interface AlbumRepository {

    suspend fun fetchAlbums(): RequestResult<List<Album>>
}