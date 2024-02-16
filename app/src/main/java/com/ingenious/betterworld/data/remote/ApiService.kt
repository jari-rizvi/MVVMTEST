package com.ingenious.betterworld.data.remote


import com.ingenious.betterworld.constants.NetworkCallPoints
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.TestModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(NetworkCallPoints.TEST)
    suspend fun getTestData(
    ): Response<TestModel>

}