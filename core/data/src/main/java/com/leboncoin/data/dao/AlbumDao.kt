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
    abstract suspend fun insertAlbums(albums: List<AlbumEntity>): List<Long>

    @Query("DELETE FROM $ALBUM_TABLE_NAME")
    abstract suspend fun deleteAlbums(): Int

    @Query("SELECT * from $ALBUM_TABLE_NAME")
    abstract suspend fun getAllAlbums(): List<AlbumEntity>

    @Query("SELECT COUNT(${AlbumColumnNames.ALBUM_ID}) FROM $ALBUM_TABLE_NAME")
    abstract suspend fun getRowCount(): Int
}