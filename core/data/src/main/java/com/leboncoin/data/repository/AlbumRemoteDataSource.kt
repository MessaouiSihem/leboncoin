package com.leboncoin.data.repository

import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album

internal interface AlbumRemoteDataSource {

    suspend fun fetchRemoteAlbums(): RequestResult<List<Album>>
}