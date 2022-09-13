package com.leboncoin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leboncoin.data.entity.AlbumColumnNames
import com.leboncoin.data.entity.AlbumEntity
import com.leboncoin.data.entity.AlbumTable.ALBUM_TABLE_NAME

@Dao
abstract class AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAlbum(albumEntity: AlbumEntity): Long

    @Query("DELETE FROM $ALBUM_TABLE_NAME WHERE ${AlbumColumnNames.ALBUM_ID} =:id")
    abstract suspend fun deleteAlbum(id: Int): Int

    @Query("SELECT * from $ALBUM_TABLE_NAME")
    abstract suspend fun getAllAlbums(): List<AlbumEntity>
}