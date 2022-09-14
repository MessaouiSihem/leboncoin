package com.leboncoin.data.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.leboncoin.data.LeBonCoinDataBase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class OCSAndroidDatabaseTest {

    //set the testing environment to use Main thread instead of background one
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context

    @Before
    fun setupTable() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @After
    fun deleteDatabase() {
        context.deleteDatabase(LeBonCoinDataBase.DATABASE_NAME)
    }
}