package com.iyam.mypagingapp.data.network.api.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iyam.mypagingapp.data.network.api.model.toUserList
import com.iyam.mypagingapp.data.network.api.service.MyPagingApiService
import com.iyam.mypagingapp.model.User

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class MyPagingApiDataSource(
    private val apiService: MyPagingApiService
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val perPage = 10
            val response = apiService.getUsers(currentPage, perPage)
            val responseData = mutableListOf<User>()
            val data = response.body()?.data?.toUserList() ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
