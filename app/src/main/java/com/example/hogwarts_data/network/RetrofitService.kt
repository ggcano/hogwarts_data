package com.example.hogwarts_data.network

import com.example.hogwarts_data.model.HousesItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("Houses")
    suspend fun getAllHouse() : Response<ArrayList<HousesItem>>

    @GET("Houses/{id}")
    suspend fun getHouseId(
        @Path("id") id: String
    ) : Response<HousesItem>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://wizard-world-api.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}