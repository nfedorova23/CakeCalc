package com.nfedorova.cakecal.presentation.ui.settings.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import com.nfedorova.cakecal.MainActivity
import com.nfedorova.cakecal.databinding.ActivityLogInBinding
import com.nfedorova.cakecal.domain.repository.sha256

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            var db = Firebase.firestore
            button.setOnClickListener{
                db.collection("users")
                    .get()
                    .addOnSuccessListener {result ->
                        for (document in result){
                            if (document.getString("email") == email.text.toString()){
                                if (document.getString("password") == sha256(pass.text.toString())){
                                    sharedPreferences.edit().putString("Email", email.text.toString()).apply()
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }
}