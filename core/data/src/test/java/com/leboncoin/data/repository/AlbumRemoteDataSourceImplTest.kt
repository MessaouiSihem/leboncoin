package com.leboncoin.data.repository

import com.leboncoin.data.api.ApiServices
import com.leboncoin.data.dto.AlbumDto
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AlbumRemoteDataSourceImplTest {

    @MockK
    private lateinit var mockAlbumMapper: AlbumMapper

    @MockK
    private lateinit var mockApiServices: ApiServices

    @MockK
    private lateinit var mockAlbumDto: AlbumDto

    @MockK
    private lateinit var mockAlbum: Album

    @MockK
    private lateinit var mockResponse: Response<List<AlbumDto>>

    @MockK
    private lateinit var mockResponseBody: ResponseBody

    private lateinit var albumRemoteDataSource: AlbumRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumRemoteDataSource = AlbumRemoteDataSourceImpl(
            mockApiServices,
            mockAlbumMapper
        )
    }

    @Test
    fun `test fetchLocalAlbums with success`() {
        coEvery {
            mockApiServices.fetchAlbums()
        } returns mockResponse
        coEvery {mockResponse.isSuccessful} returns true
        coEvery {mockResponse.body()} returns listOf(mockAlbumDto)
        coEvery {mockAlbumMapper.convertDtoToModel(mockAlbumDto)} returns mockAlbum

        //
        val result = runBlocking {
            albumRemoteDataSource.fetchRemoteAlbums()
        }
        //
        Assert.assertTrue(result is RequestResult.Success)
        coVerify {
            mockApiServices.fetchAlbums()
        }
        coVerify {
            mockAlbumMapper.convertDtoToModel(mockAlbumDto)
        }
    }

    @Test
    fun `test fetchLocalAlbums with error`() {
        coEvery {
            mockApiServices.fetchAlbums()
        } returns mockResponse
        coEvery {mockResponse.isSuccessful} returns false
        coEvery {mockResponse.errorBody()} returns mockResponseBody

        //
        val result = runBlocking {
            albumRemoteDataSource.fetchRemoteAlbums()
        }
        //
        Assert.assertTrue(result is RequestResult.Error)
        coVerify {
            mockApiServices.fetchAlbums()
        }
    }
}