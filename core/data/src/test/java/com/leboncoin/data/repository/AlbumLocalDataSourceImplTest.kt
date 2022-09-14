package com.leboncoin.data.repository

import com.leboncoin.data.dao.AlbumDao
import com.leboncoin.data.entity.AlbumEntity
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.models.Album
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumLocalDataSourceImplTest {

    @MockK
    private lateinit var mockAlbumMapper: AlbumMapper

    @MockK
    private lateinit var mockAlbumDao: AlbumDao

    @MockK
    private lateinit var mockAlbum: Album

    @MockK
    private lateinit var mockAlbumEntity: AlbumEntity

    private lateinit var albumLocalDataSource: AlbumLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumLocalDataSource = AlbumLocalDataSourceImpl(
            mockAlbumMapper,
            mockAlbumDao
        )
    }

    @Test
    fun `test fetchLocalAlbums`() {
        coEvery {
            mockAlbumDao.getAllAlbums()
        } returns listOf(mockAlbumEntity)
        coEvery {
            mockAlbumMapper.convertEntityToModel(mockAlbumEntity)
        } returns mockAlbum

        //
        val result = runBlocking {
            albumLocalDataSource.fetchLocalAlbums()
        }
        //
        Assert.assertEquals(
            result,
            listOf(mockAlbum)
        )
        coVerify {
            mockAlbumDao.getAllAlbums()
        }
        coVerify {
            mockAlbumMapper.convertEntityToModel(mockAlbumEntity)
        }
    }

    @Test
    fun `test saveLocalAlbums`() {
        coEvery {
            mockAlbumDao.insertAlbums(listOf(mockAlbumEntity))
        } returns listOf(1L)
        coEvery {
            mockAlbumMapper.convertModelToEntity(mockAlbum)
        } returns mockAlbumEntity

        //
        val result = runBlocking {
            albumLocalDataSource.saveLocalAlbums(listOf(mockAlbum))
        }
        //
        coVerify {
            mockAlbumDao.insertAlbums(listOf(mockAlbumEntity))
        }
        coVerify {
            mockAlbumMapper.convertModelToEntity(mockAlbum)
        }
    }
}