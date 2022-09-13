package com.leboncoin.domain.usecase

import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.AlbumRepository

class FetchAlbumsUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(): RequestResult<List<Album>> {
        return albumRepository.fetchAlbums()
    }
}