package com.example.flowretrofitproject.model.api

import com.example.flowretrofitproject.model.data.ApiResponse
import com.example.flowretrofitproject.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
// это просто обычный интерфейс
interface ApiService {

    @GET(Constants.RANDOM_URL)
    suspend fun getData(): Response<ApiResponse>

}