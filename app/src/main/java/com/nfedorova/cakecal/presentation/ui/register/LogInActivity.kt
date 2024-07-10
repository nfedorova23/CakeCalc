package com.nfedorova.cakecal.presentation.ui.register

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.databinding.ActivityLogInBinding
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.presentation.state.viewmodel.login.LogInViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.login.LogInViewModelFactory
import com.nfedorova.cakecal.presentation.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInActivity : AppCompatActivity(), ChangeOfActivityLogIn {

    private lateinit var binding: ActivityLogInBinding
    private val viewModel by viewModel<LogInViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("KEY", "-9") != "-9") {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            val signInText: TextView = binding.signinTv
            signInText.setOnClickListener {
                startActivity(Intent(this, SignInActivity::class.java))
            }

            val email: TextView = binding.editTextTextEmailAddress
            val pass: TextView = binding.editTextTextPassword
            val button = binding.button

            button.setOnClickListener {
                viewModel.checkRegister(email = email, pass = pass, change = this)
            }
        }
    }

    override fun changeOfActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}