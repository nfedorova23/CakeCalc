package com.nfedorova.cakecal.presentation.ui.register

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nfedorova.cakecal.presentation.ui.MainActivity
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.databinding.ActivityLogInBinding
import com.nfedorova.cakecal.domain.usecase.CheckRegistrationByEmailUseCase
import com.nfedorova.cakecal.domain.model.LoginUser

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val userRepository by lazy { UserRepositoryImpl(dataSource = UserDataSourceImpl(context = applicationContext)) }
    private val checkRegistrationByEmailUseCase by lazy { CheckRegistrationByEmailUseCase(userRepository = userRepository) }
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

            button.setOnClickListener{
                val user = LoginUser(email = email, password = pass)
                checkRegistrationByEmailUseCase.execute(user = user)
            }
        }
    }
}