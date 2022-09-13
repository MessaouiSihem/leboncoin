package com.leboncoin.data.mapper

import com.leboncoin.data.dto.AlbumDto
import com.leboncoin.data.entity.AlbumEntity
import com.leboncoin.domain.models.Album

internal class AlbumMapper {

    fun convertDtoToModel(albumDto: AlbumDto?) =
        Album(
            id = albumDto?.id ?: -1,
            title = albumDto?.title ?: "",
            thumbnailUrl = albumDto?.thumbnailUrl ?: ""
        )

    fun convertEntityToModel(albumEntity: AlbumEntity) =
        Album(
            id = albumEntity.id,
            title = albumEntity.title,
            thumbnailUrl = albumEntity.thumbnailUrl
        )

    fun convertModelToEntity(album : Album) =
        AlbumEntity(
            id = album.id,
            title = album.title,
            thumbnailUrl = album.thumbnailUrl
        )
}