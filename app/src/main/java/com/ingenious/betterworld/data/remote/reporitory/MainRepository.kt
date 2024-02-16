package com.ingenious.betterworld.data.remote.reporitory

import com.ingenious.betterworld.data.local.db.AppDao
import com.ingenious.betterworld.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getTestData(
    ) = apiService.getTestData()
}