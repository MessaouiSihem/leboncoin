package com.leboncoin.data.dto

import com.leboncoin.data.AbstractTest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.Assert
import org.junit.Test

class AlbumDtoTest : AbstractTest() {

    @Test
    fun `test parse json to AlbumDto`() {
        val jsonString = readFile("json/albums.json")
        val moshiBuilder = Moshi.Builder()
            .build()
        val type = Types.newParameterizedType(List::class.java, AlbumDto::class.java)
        val jsonAdapter: JsonAdapter<List<AlbumDto>> = moshiBuilder.adapter(
            type
        )
        val albumListDto = jsonAdapter.fromJson(jsonString)

        //
        Assert.assertNotNull(albumListDto)
        Assert.assertEquals(
            3,
            albumListDto?.size
        )
        //
        val albumDto = albumListDto?.get(0)
        Assert.assertNotNull(albumDto)
        Assert.assertEquals(
            1,
            albumDto?.id
        )
        Assert.assertEquals(
            "https://via.placeholder.com/600/92c952",
            albumDto?.url
        )
        Assert.assertEquals(
            "accusamus beatae ad facilis cum similique qui sunt",
            albumDto?.title
        )
        Assert.assertEquals(
            "https://via.placeholder.com/150/92c952",
            albumDto?.thumbnailUrl
        )
    }
}