package com.leboncoin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leboncoin.data.entity.AlbumColumnNames.ALBUM_ID
import com.leboncoin.data.entity.AlbumColumnNames.ALBUM_THUMBNAIL_URL
import com.leboncoin.data.entity.AlbumColumnNames.ALBUM_TITLE
import com.leboncoin.data.entity.AlbumTable.ALBUM_TABLE_NAME

@Entity(tableName = ALBUM_TABLE_NAME)
data class AlbumEntity(
    @PrimaryKey @ColumnInfo(name = ALBUM_ID) val id: Int,
    @ColumnInfo(name = ALBUM_TITLE) val title: String,
    @ColumnInfo(name = ALBUM_THUMBNAIL_URL) val thumbnailUrl: String
)

object AlbumTable {

    const val ALBUM_TABLE_NAME = "album_table"
}

object AlbumColumnNames {

    const val ALBUM_ID = "album_id"
    const val ALBUM_TITLE = "album_title"
    const val ALBUM_THUMBNAIL_URL = "album_thumbnail_url"
}