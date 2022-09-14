package com.leboncoin.data.mapper

import com.leboncoin.data.dto.AlbumDto
import com.leboncoin.data.entity.AlbumEntity
import com.leboncoin.domain.models.Album
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumMapperTest {

    private lateinit var albumMapper: AlbumMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumMapper = AlbumMapper()
    }

    @Test
    fun `tests convert Entity to Model`() {
        val entity = AlbumEntity(
            id = 1,
            title = "title",
            thumbnailUrl = "thumbnailUrl"
        )
        //
        val model = albumMapper.convertEntityToModel(entity)
        //
        Assert.assertEquals(
            entity.id,
            model.id
        )
        Assert.assertEquals(
            entity.title,
            model.title
        )
        Assert.assertEquals(
            entity.thumbnailUrl,
            model.thumbnailUrl
        )
    }

    @Test
    fun `tests convert dto to Model`() {
        val dto = AlbumDto(
            id = 1,
            title = "title",
            url = "url",
            thumbnailUrl = "thumbnailUrl"
        )
        //
        val model = albumMapper.convertDtoToModel(dto)
        //
        Assert.assertEquals(
            dto.id,
            model.id
        )
        Assert.assertEquals(
            dto.title,
            model.title
        )
        Assert.assertEquals(
            dto.thumbnailUrl,
            model.thumbnailUrl
        )
    }

    @Test
    fun `tests convert dto to Model with nul values`() {
        val dto = AlbumDto(
            id = null,
            title = null,
            url = null,
            thumbnailUrl = null
        )
        //
        val model = albumMapper.convertDtoToModel(dto)
        //
        Assert.assertEquals(
            -1,
            model.id
        )
        Assert.assertEquals(
            "",
            model.title
        )
        Assert.assertEquals(
            "",
            model.thumbnailUrl
        )
    }

    @Test
    fun `tests convert Model to Entity`() {
        val model = Album(
            id = 1,
            title = "title",
            thumbnailUrl = "thumbnailUrl"
        )
        //
        val entity = albumMapper.convertModelToEntity(model)
        //
        Assert.assertEquals(
            model.id,
            entity.id
        )
        Assert.assertEquals(
            model.title,
            entity.title
        )
        Assert.assertEquals(
            model.thumbnailUrl,
            entity.thumbnailUrl
        )
    }
}