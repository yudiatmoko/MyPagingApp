package com.iyam.mypagingapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iyam.mypagingapp.data.network.api.datasource.MyPagingApiDataSource
import com.iyam.mypagingapp.data.network.api.service.MyPagingApiService

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MainActivityViewModel(
    private val apiService: MyPagingApiService
) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 1)) {
        MyPagingApiDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
