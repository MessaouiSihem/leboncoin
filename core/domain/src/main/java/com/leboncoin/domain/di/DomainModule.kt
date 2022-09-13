package com.leboncoin.domain.di

import com.leboncoin.domain.usecase.FetchAlbumsUseCase
import org.koin.dsl.module

val domainModule = module {
    // UseCases
    factory {
        FetchAlbumsUseCase(get())
    }
}
