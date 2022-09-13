package com.leboncoin.data.repository

import com.leboncoin.data.utils.DataHelper
import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val albumLocalDataSource: AlbumLocalDataSource,
    private val albumRemoteDataSource: AlbumRemoteDataSource,
    private val dataHelper: DataHelper
) : AlbumRepository {

    override suspend fun fetchAlbums(): RequestResult<List<Album>> {
        return if (dataHelper.isInternetAvailable()) {
            getFromRemote()
        } else getFromLocal()
    }

    private suspend fun getFromRemote(): RequestResult<List<Album>> {
        return albumRemoteDataSource.fetchRemoteAlbums().also {
            syncAlbums(it)
        }
    }

    private suspend fun getFromLocal(): RequestResult<List<Album>> {
       return RequestResult.Success(albumLocalDataSource.fetchLocalAlbums())
    }

    private suspend fun syncAlbums(requestResult : RequestResult<List<Album>>) {
        if(requestResult is RequestResult.Success){
            albumLocalDataSource.saveLocalAlbums(requestResult.result)
        }
    }
}

