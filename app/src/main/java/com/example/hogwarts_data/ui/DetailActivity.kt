package com.example.hogwarts_data.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.ViewModelProvider
import com.example.hogwarts_data.adapter.TraitsAdapter

import com.example.hogwarts_data.repo.MainRepository
import com.example.hogwarts_data.viewmodel.MainViewModel
import com.example.hogwarts_data.repo.MyViewModelFactory
import com.example.hogwarts_data.network.RetrofitService
import com.example.hogwarts_data.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    lateinit var viewModel: MainViewModel
    private val adapter = TraitsAdapter()
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
        observerHouse()

       viewModel.traitList.observe(this) {
            adapter.setTraits(it)
        }

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun observerHouse() {
        viewModel.house.observe(this) {
            binding.textViewAnimal.text = it.animal
            binding.textViewFounder.text = it.founder
            binding.textViewMainHouse.text = it.name
            binding.textViewGhost.text = it.ghost
            binding.textViewComnmonRoom.text = it.commonRoom
            binding.textViewElement.text = it.element
            binding.textViewHouseColours.text = it.houseColours
            binding.textViewTraits.text = it.traits.toString()
            binding.recyclerviewHead.adapter = adapter
        }
    }
}