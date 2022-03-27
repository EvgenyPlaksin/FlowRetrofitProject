package com.example.flowretrofitproject.model.datasource

import com.example.flowretrofitproject.model.api.ApiService
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getData() = apiService.getData()

}
