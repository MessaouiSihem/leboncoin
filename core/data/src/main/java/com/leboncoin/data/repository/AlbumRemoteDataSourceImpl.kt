package com.leboncoin.data.repository

import com.leboncoin.domain.RequestResult
import com.leboncoin.data.api.ApiServices
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.AlbumRepository

internal class AlbumRemoteDataSourceImpl(
    private val apiServices: ApiServices,
    private val albumMapper: AlbumMapper,
) : AlbumRemoteDataSource {

    override suspend fun fetchRemoteAlbums(): RequestResult<List<Album>> {
        val response = apiServices.fetchAlbums()
        if (response.isSuccessful) {
            val albumDtos = response.body()
            return if (albumDtos == null) {
                RequestResult.Error(
                    "fetchFromNetwork returns null"
                )
            } else {
                RequestResult.Success(result = albumDtos.map { albumDto ->
                    albumMapper.convertDtoToModel(
                        albumDto
                    )
                })
            }
        } else {
            return RequestResult.Error(
                response.errorBody()
                    .toString()
            )
        }
    }
}