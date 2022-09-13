package com.leboncoin.leboncoinapp.di

import com.leboncoin.leboncoinapp.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModels
    viewModel {
        MainViewModel(
            get()
        )
    }
}
