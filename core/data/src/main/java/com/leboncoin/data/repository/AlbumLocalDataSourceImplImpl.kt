package com.leboncoin.data.repository

import com.leboncoin.data.dao.AlbumDao
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.models.Album

internal class AlbumLocalDataSourceImplImpl(
    private val albumMapper: AlbumMapper,
    private val albumDao: AlbumDao,
) :
    AlbumLocalDataSource {

    override suspend fun fetchLocalAlbums(): List<Album> {
        return albumDao.getAllAlbums().map { albumEntity ->
            albumMapper.convertEntityToModel(albumEntity)
        }
    }

    override suspend fun saveLocalAlbums(albumList: List<Album>) {
        albumList.map { album ->
            albumDao.addAlbum(albumEntity = albumMapper.convertModelToEntity(album))
        }
    }
}