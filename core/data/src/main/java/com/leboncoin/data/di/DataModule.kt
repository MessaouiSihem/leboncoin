package com.leboncoin.data.di

import android.content.Context
import com.leboncoin.data.utils.DataConstants.Companion.BASE_URL
import com.leboncoin.data.utils.DataConstants.Companion.CACHE_SIZE
import com.leboncoin.data.utils.DataConstants.Companion.CONNECT_TIMEOUT_SECONDS
import com.leboncoin.data.utils.DataConstants.Companion.READ_TIMEOUT_SECONDS
import com.leboncoin.data.utils.DataHelper
import com.leboncoin.data.LeBonCoinDataBase
import com.leboncoin.data.api.ApiServices
import com.leboncoin.data.mapper.AlbumMapper
import com.leboncoin.data.repository.*
import com.leboncoin.domain.repository.AlbumRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = createDataModule()

fun createDataModule() = module {
    factory {
        val cache: Cache = get()
        buildApiServices(
            cache
        )
    }

    single {
        Cache(
            get<Context>().cacheDir,
            CACHE_SIZE
        )
    }

    // Repositories
    factory {
        AlbumRepositoryImpl(
            get(),
            get(),
            get()
        ) as AlbumRepository
    }

    factory {
        AlbumRemoteDataSourceImplImpl(
            get(),
            get()
        ) as AlbumRemoteDataSource
    }

    factory {
        AlbumLocalDataSourceImplImpl(
            get(),
            get()
        ) as AlbumLocalDataSource
    }

    // Mappers
    factory {
        AlbumMapper()
    }

    // Database
    single { LeBonCoinDataBase.getInstance(androidContext()) }
    single { (get() as LeBonCoinDataBase).albumDao() }

    // Helper
    single {
        val context = get<Context>()
        DataHelper(context)
    }
}

private fun buildApiServices(
    cache: Cache,
): ApiServices {
    return Retrofit.Builder()
        .client(
            buildOkHttpClient(
                cache
            )
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiServices::class.java)
}

private fun buildOkHttpClient(
    cache: Cache,
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(
            CONNECT_TIMEOUT_SECONDS,
            TimeUnit.SECONDS
        )
        .readTimeout(
            READ_TIMEOUT_SECONDS,
            TimeUnit.SECONDS
        )
    return builder.build()
}