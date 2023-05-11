package com.example.hogwarts_data

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllHouse() = retrofitService.getAllHouse()
    suspend fun getHouseId(id:String) = retrofitService.getHouseId(id)

}