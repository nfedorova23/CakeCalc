package com.nfedorova.cakecal.data.datasource.database

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.MainActivity
import com.nfedorova.cakecal.data.datasource.UserDataSource
import com.nfedorova.cakecal.data.datasource.mapper.sha256
import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO
import com.nfedorova.cakecal.presentation.ui.register.LogInActivity

class UserDataSourceImpl(private val context: Context) : UserDataSource {

    private val sharedPreferences = context.getSharedPreferences("KEY", Context.MODE_PRIVATE).edit()
    override fun addDB(userDBO: UserDBO): Boolean {
        val user = hashMapOf(
            "name" to userDBO.name.text.toString(),
            "email" to userDBO.email.text.toString(),
            "password" to sha256(userDBO.password.text.toString())
        )

        Firebase.firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                sharedPreferences.putString("Name", userDBO.name.text.toString()).apply()
                context.startActivity(Intent(context, LogInActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
        return true
    }

    override fun checkData(loginUserDBO: LoginUserDBO): Boolean {
        Firebase.firestore.collection("users")
            .get()
            .addOnSuccessListener {result ->
                for (document in result){
                    if (document.getString("email") == loginUserDBO.email.text.toString()){
                        if (document.getString("password") == sha256(loginUserDBO.password.text.toString())){
                            sharedPreferences.putString("Email", loginUserDBO.email.text.toString()).apply()
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    }
                }
            }
            .addOnFailureListener{
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
        return true
    }

    override fun logUot(): Boolean {
        if (sharedPreferences != null) {
                sharedPreferences.remove("KEY").apply()
                context.startActivity(Intent(context, LogInActivity::class.java))
            return true
            }
        return false
    }
}