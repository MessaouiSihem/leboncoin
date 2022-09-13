package com.leboncoin.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AlbumDto(
    @field:Json(name = "id") var id: Int?,
    @field:Json(name = "title") var title: String?,
    @field:Json(name = "url") var url: String?,
    @field:Json(name = "thumbnailUrl") var thumbnailUrl: String?
)