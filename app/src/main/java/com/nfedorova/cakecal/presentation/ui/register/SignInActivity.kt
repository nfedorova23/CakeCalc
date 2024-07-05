package com.nfedorova.cakecal.presentation.ui.register

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.databinding.ActivitySignInBinding
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn
import com.nfedorova.cakecal.presentation.state.viewmodel.signin.SignInViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.signin.SignInViewModelFactory

class SignInActivity : AppCompatActivity(), ChangeOfActivitySignIn {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, SignInViewModelFactory(this))[SignInViewModel::class.java]

        val name: TextView = binding.editTextName
        val email: TextView = binding.editTextTextEmailAddress
        val pass: TextView = binding.editTextTextPassword

        val logInText: TextView = binding.loginTv
        logInText.setOnClickListener {
            switchToLogInActivity()
        }

        val button = binding.button
        button.setOnClickListener {
            viewModel.register(name = name, email = email, pass = pass, change = this)
        }
    }

    private fun switchToLogInActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    override fun changeOfActivity() {
        startActivity(Intent(this, LogInActivity::class.java))
    }
}