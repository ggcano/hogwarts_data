package com.example.hogwarts_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.ViewModelProvider
import com.example.hogwarts_data.databinding.ActivityDetailBinding
import com.example.hogwarts_data.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        val id = intent.getStringExtra("value_id")
        viewModel.getHouse(id.toString())
        adada()
    }

    private fun adada (){
        viewModel.house.observe(this) {
            binding.textViewAnimal.text = it.animal
        }
    }
}