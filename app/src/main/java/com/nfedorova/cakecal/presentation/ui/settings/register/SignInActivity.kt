package com.nfedorova.cakecal.presentation.ui.settings.register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.MainActivity
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var sharedPreferences = getSharedPreferences("KEY", Context.MODE_PRIVATE).edit()
        var name: TextView = binding.editTextName
        var email: TextView = binding.editTextTextEmailAddress
        var pass: TextView = binding.editTextTextPassword
        var button = binding.button
        button.setOnClickListener {
            if (name.text.isEmpty()){
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
            }
            if (email.text.isEmpty() || !email.text.contains("@")){
                Toast.makeText(this, "Почта введена неверно", Toast.LENGTH_SHORT).show()
            }
            if (pass.text.isEmpty() || pass.text.length < 6){
                Toast.makeText(this, "Пароль должен быть больше 6 символов", Toast.LENGTH_SHORT).show()
            }
            else{
                val db = Firebase.firestore

                val user = hashMapOf(
                    "name" to name.text.toString(),
                    "email" to email.text.toString(),
                    "password" to pass.text.toString()
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        sharedPreferences.putString("Name", name.text.toString()).commit()
                        //если не работает перенести за пределы коллекции
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
                    }
                //перенести вот сюда
            }
        }
    }
}