package com.nfedorova.cakecal.presentation.ui.register

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.databinding.ActivitySignInBinding
import com.nfedorova.cakecal.domain.usecase.RegisterByEmailUseCase
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn

class SignInActivity : AppCompatActivity(), ChangeOfActivitySignIn {

    private lateinit var binding: ActivitySignInBinding
    private val userRepository by lazy { UserRepositoryImpl(dataSource = UserDataSourceImpl(context = applicationContext)) }
    private val registrationByEmailUseCase by lazy { RegisterByEmailUseCase(userRepository = userRepository, context = applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name: TextView = binding.editTextName
        val email: TextView = binding.editTextTextEmailAddress
        val pass: TextView = binding.editTextTextPassword

        val logInText: TextView = binding.loginTv
        logInText.setOnClickListener {
            switchToLogInActivity()
        }

        val button = binding.button
        button.setOnClickListener {
            val user = User(name = name, email = email, password = pass)
            registrationByEmailUseCase.execute(user = user, change = this)
        }
    }

    private fun switchToLogInActivity(){
        val intent = Intent(this, LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    override fun changeOfActivity() {
        startActivity(Intent(this, LogInActivity::class.java))
    }
}