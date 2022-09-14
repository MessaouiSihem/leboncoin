package com.leboncoin.leboncoinapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leboncoin.domain.RequestResult
import com.leboncoin.domain.models.Album
import com.leboncoin.domain.usecase.FetchAlbumsUseCase
import com.leboncoin.leboncoinapp.MainCoroutineRule
import com.leboncoin.leboncoinapp.getValueForTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineRule()

    @MockK
    private lateinit var mockFetchAlbumsUseCase: FetchAlbumsUseCase

    @MockK
    private lateinit var mockRequestResult: RequestResult<List<Album>>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(mockFetchAlbumsUseCase)
    }

    @Test
    fun `test fetchAlbums`() {
        coEvery {
            mockFetchAlbumsUseCase.execute()
        } returns mockRequestResult
        //
        mainViewModel.fetchAlbums()
        //
        coVerify {
            mockFetchAlbumsUseCase.execute()
        }
        //
        Assert.assertEquals(
            mockRequestResult,
            mainViewModel.albumsLiveData.getValueForTest()
        )
    }
}