package com.leboncoin.data.api

import com.leboncoin.data.dto.AlbumDto
import retrofit2.Response
import retrofit2.http.GET

internal interface ApiServices {

    @GET("technical-test.json")
    suspend fun fetchAlbums(): Response<List<AlbumDto>>
}