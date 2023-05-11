package com.example.hogwarts_data.repo

import com.example.hogwarts_data.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllHouse() = retrofitService.getAllHouse()
    suspend fun getHouseId(id:String) = retrofitService.getHouseId(id)

}