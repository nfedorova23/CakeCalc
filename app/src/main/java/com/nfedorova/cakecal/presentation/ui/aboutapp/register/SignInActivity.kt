package com.nfedorova.cakecal.presentation.ui.aboutapp.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.databinding.ActivitySignInBinding
import com.nfedorova.cakecal.domain.repository.sha256

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("KEY", Context.MODE_PRIVATE).edit()
        val name: TextView = binding.editTextName
        val email: TextView = binding.editTextTextEmailAddress
        val pass: TextView = binding.editTextTextPassword

        val logInText: TextView = binding.loginTv
        logInText.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }

        val button = binding.button
        button.setOnClickListener {
            if (name.text.isEmpty()){
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.text.isEmpty() || !email.text.contains("@")){
                Toast.makeText(this, "Почта введена неверно", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass.text.isEmpty() || pass.text.length < 6){
                Toast.makeText(this, "Пароль должен быть больше 6 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

                val db = Firebase.firestore

                val user = hashMapOf(
                    "name" to name.text.toString(),
                    "email" to email.text.toString(),
                    "password" to sha256(pass.text.toString())
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        sharedPreferences.putString("Name", name.text.toString()).apply()
                        startActivity(Intent(this, LogInActivity::class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
                    }

        }
    }
}