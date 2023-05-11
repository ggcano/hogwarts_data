package com.example.hogwarts_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hogwarts_data.databinding.ActivityLoginBinding
import com.example.hogwarts_data.helper.Constant
import com.example.hogwarts_data.helper.PrefHelper

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        prefHelper = PrefHelper(this)
        binding.apply {
            buttonLogin.setOnClickListener {
                if (editUsername.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                    saveSession(editUsername.text.toString(), editPassword.text.toString())
                    if (binding.editUsername.text.toString() == "Paco"
                        && binding.editPassword.text.toString() == "1111"
                    ) {
                        moveIntent()
                    } else if (binding.editUsername.text.toString() == "Raquel"
                        && binding.editPassword.text.toString() == "2222"
                    ) {
                        moveIntent()
                    } else {
                        showMessage("Por favor, logeate correctamente")
                        prefHelper.put(Constant.PREF_IS_LOGIN, false)
                    }
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        if (prefHelper.getBoolean(Constant.PREF_IS_LOGIN)) {
            moveIntent()
        }
    }

    private fun saveSession(username: String, password: String) {
        prefHelper.put(Constant.PREF_USERNAME, username)
        prefHelper.put(Constant.PREF_PASSWORD, password)
        prefHelper.put(Constant.PREF_IS_LOGIN, true)


    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()


    }


    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}