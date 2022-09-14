package com.leboncoin.data.repository

import com.leboncoin.data.utils.DataHelper
import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumRepositoryImplTest {

    @MockK
    private lateinit var mockAlbumLocalDataSource: AlbumLocalDataSource

    @MockK
    private lateinit var mockAlbumRemoteDataSource: AlbumRemoteDataSource

    @MockK
    private lateinit var mockDataHelper: DataHelper

    @MockK
    private lateinit var mockRequestResult: RequestResult.Success<List<Album>>

    @MockK
    private lateinit var mockAlbum: Album

    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumRepository = AlbumRepositoryImpl(
            mockAlbumLocalDataSource,
            mockAlbumRemoteDataSource,
            mockDataHelper
        )
    }

    @Test
    fun `test fetchAlbums when internet is available`() {
        coEvery {
            mockDataHelper.isInternetAvailable()
        } returns true
        coEvery {
            mockAlbumRemoteDataSource.fetchRemoteAlbums()
        } returns mockRequestResult
        coEvery {
            mockRequestResult.result
        } returns listOf(mockAlbum)
        coEvery {
            mockAlbumLocalDataSource.saveLocalAlbums(listOf(mockAlbum))
        } returns Unit

        //
        val result = runBlocking {
            albumRepository.fetchAlbums()
        }
        //
        Assert.assertTrue(result is RequestResult.Success)
        coVerify {
            mockDataHelper.isInternetAvailable()
        }
        coVerify {
            mockAlbumRemoteDataSource.fetchRemoteAlbums()
        }
        coVerify {
            mockAlbumLocalDataSource.saveLocalAlbums(listOf(mockAlbum))
        }
    }

    @Test
    fun `test fetchAlbums when internet is unavailable`() {
        coEvery {
            mockDataHelper.isInternetAvailable()
        } returns false
        coEvery {
            mockAlbumLocalDataSource.fetchLocalAlbums()
        } returns listOf(mockAlbum)

        //
        val result = runBlocking {
            albumRepository.fetchAlbums()
        }
        //
        Assert.assertTrue(result is RequestResult.Success)

    }
}