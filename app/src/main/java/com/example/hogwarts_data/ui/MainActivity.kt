package com.example.hogwarts_data.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hogwarts_data.adapter.HogwartsAdapter
import com.example.hogwarts_data.repo.MainRepository
import com.example.hogwarts_data.viewmodel.MainViewModel
import com.example.hogwarts_data.repo.MyViewModelFactory
import com.example.hogwarts_data.network.RetrofitService
import com.example.hogwarts_data.databinding.ActivityMainBinding
import com.example.hogwarts_data.helper.Constant
import com.example.hogwarts_data.helper.PrefHelper


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var prefHelper: PrefHelper

    private val adapter = HogwartsAdapter()
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)
        setSupportActionBar(binding.myToolbar)
        setupToolbar()


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(
            this, MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        viewModel.getAllHouse()
        viewModel.houseList.observe(this) {
            adapter.setHouse(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

     adapter.onItemClick = { id ->
            goToDetailScreen(id)
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupToolbar() {

        setSupportActionBar(binding.myToolbar)

        binding.myToolbar.title = prefHelper.getString(Constant.PREF_USERNAME)


        // Crea una instancia del botón y configúralo
        val button = Button(this)
        button.text = "Logout"
        binding.myToolbar.addView(button)
        button.setOnClickListener {
            prefHelper.clear()
            showMessage("Por Favor, inicia Sesion de nuevo")
            moveIntent()
        }

       //
    }

  /*  private fun itemClick() {
        adapter.onItemClick = {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }
    }*/

    private fun goToDetailScreen(value: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("value_id", value)
        startActivity(intent)
    }

}