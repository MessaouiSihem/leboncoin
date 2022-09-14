package com.leboncoin.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.leboncoin.data.LeBonCoinDataBase
import com.leboncoin.data.entity.AlbumEntity
import kotlinx.coroutines.runBlocking
import org.junit.*

class AlbumDaoTest {

    //set the testing environment to use Main thread instead of background one
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var albumDao: AlbumDao

    @Before
    fun setupEmptyTable() {
        tearDown()
        LeBonCoinDataBase.TEST_MODE = true
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        albumDao = LeBonCoinDataBase.getInstance(appContext).albumDao()

        runBlocking {
            albumDao.deleteAlbums()
        }
    }

    @After
    fun tearDown() {
        LeBonCoinDataBase.closeAndDestroy()
    }

    ///////////////////////////////////
    //REQUESTS ON EMPTY TABLE
    ///////////////////////////////////

    @Test
    fun testGetAlbumsOnEmptyTable() {
        Assert.assertEquals(listOf<AlbumEntity>(),
            runBlocking {
            albumDao.getAllAlbums()
        })
    }

    @Test
    fun testGetAllAlbumsOnNonEmptyTable() {
        val expectedQuantity = 3
        insertAndCheck(expectedQuantity)
        Assert.assertNotNull(runBlocking {
            albumDao.getAllAlbums()
        })
        assertTableSize(3)
    }

    @Test
    fun testDeleteAlbumsOnEmptyTable() {
        Assert.assertEquals(0,
            runBlocking {
                albumDao.deleteAlbums()
            })
        assertTableSize(0)
    }

    ///////////////////////////////////
    //INSERTS
    ///////////////////////////////////

    @Test
    fun testInsertAlbums() {
        val expectedQuantity = 7
        insertAndCheck(expectedQuantity)
        assertTableSize(expectedQuantity)
    }

    @Test
    fun testInsertAlbumsConflict() {
        val expectedQuantity = 9
        insertAndCheck(expectedQuantity)
        //insert again same albums entities
        insertAndCheck(expectedQuantity)
        assertTableSize(expectedQuantity)
    }

    ///////////////////////////////////
    //REQUESTS on non empty tables
    ///////////////////////////////////

    @Test
    fun testGetAlbumsCount() {
        val expectedQuantity = 13
        insertAndCheck(expectedQuantity)
        assertTableSize(expectedQuantity)
    }

    @Test
    fun testDeleteAlbumsOnNonEmptyTable() {
        Assert.assertEquals(5,
            runBlocking {
                albumDao.insertAlbums(createAlbumsEntities(5))
                albumDao.deleteAlbums()
            })
        assertTableSize(0)
    }

    ///////////////////////////////////
    //UTILS
    ///////////////////////////////////

    private fun assertTableSize(size: Int) {
        Assert.assertEquals(size,
            runBlocking {
                albumDao.getRowCount()
            })
    }

    private fun insertAndCheck(quantity: Int) {
        Assert.assertEquals(
            quantity,
            runBlocking {
                albumDao.insertAlbums(createAlbumsEntities(quantity))
            }.size
        )
    }

    private fun createAlbumsEntities(quantity: Int = 23): List<AlbumEntity> {
        val list = mutableListOf<AlbumEntity>()
        for (i in 1..quantity) {
            list.add(
                AlbumEntity(
                    id = i,
                    title = "title$i",
                    thumbnailUrl = "image$i"
                )
            )
        }
        return list
    }

}