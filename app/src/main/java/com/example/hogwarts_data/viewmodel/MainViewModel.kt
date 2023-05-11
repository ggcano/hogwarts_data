package com.example.hogwarts_data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hogwarts_data.model.HousesItem
import com.example.hogwarts_data.model.Trait
import com.example.hogwarts_data.repo.MainRepository
import kotlinx.coroutines.*


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

        val errorMessage = MutableLiveData<String>()
        val houseList = MutableLiveData<ArrayList<HousesItem>>()
        val traitList = MutableLiveData<List<Trait>>()
        val house = MutableLiveData<HousesItem>()
        var job: Job? = null

        val loading = MutableLiveData<Boolean>()

        fun getAllHouse() {
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = mainRepository.getAllHouse()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        houseList.postValue(response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }

        }
    fun getHouse(id:String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getHouseId(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    house.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }


        private fun onError(message: String) {
            errorMessage.value = message
            loading.value = false
        }

        override fun onCleared() {
            super.onCleared()
            job?.cancel()
        }

    }
