package com.leboncoin.domain.usecase

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

class FetchAlbumsUseCaseTest {

    @MockK
    private lateinit var mockAlbumRepository: AlbumRepository

    @MockK
    private lateinit var mockRequestResult: RequestResult<List<Album>>

    private lateinit var useCase: FetchAlbumsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = FetchAlbumsUseCase(albumRepository = mockAlbumRepository)
    }

    @Test
    fun `test execute with successful result`() {
        coEvery {
            mockAlbumRepository.fetchAlbums()
        } returns mockRequestResult

        val output = runBlocking {
            useCase.execute()
        }

        //
        coVerify {
            mockAlbumRepository.fetchAlbums()
        }
        Assert.assertEquals(
            output,
            mockRequestResult
        )
    }

    @Test
    fun `test execute with failed result`() {
        val result = RequestResult.Error(errorResponse = "error_message")

        coEvery {
            mockAlbumRepository.fetchAlbums()
        } returns result

        //
        val output = runBlocking {
            useCase.execute()
        }

        //
        coVerify {
            mockAlbumRepository.fetchAlbums()
        }
        Assert.assertEquals(
            output,
            result
        )
    }
}