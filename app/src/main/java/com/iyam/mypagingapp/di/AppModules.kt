package com.iyam.mypagingapp.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.iyam.mypagingapp.data.network.api.datasource.MyPagingApiDataSource
import com.iyam.mypagingapp.data.network.api.service.MyPagingApiService
import com.iyam.mypagingapp.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {
    private val networkModule = module {
        single { ChuckerInterceptor(androidContext()) }
        single { MyPagingApiService.invoke(get()) }
    }

    private val dataSourceModule = module {
        single<MyPagingApiDataSource> { MyPagingApiDataSource(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::MainActivityViewModel)
    }

    val modules: List<Module> = listOf(
        networkModule,
        dataSourceModule,
        viewModelModule
    )
}
