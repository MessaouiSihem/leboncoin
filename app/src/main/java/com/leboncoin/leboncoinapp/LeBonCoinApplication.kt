package com.leboncoin.leboncoinapp

import android.app.Application
import com.leboncoin.data.di.dataModule
import com.leboncoin.domain.di.domainModule
import com.leboncoin.leboncoinapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LeBonCoinApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    private fun configureDi() {
        startKoin {

            androidContext(this@LeBonCoinApplication)

            modules(
                listOf(
                    appModule,
                    domainModule,
                    dataModule
                )
            )
        }
    }
}