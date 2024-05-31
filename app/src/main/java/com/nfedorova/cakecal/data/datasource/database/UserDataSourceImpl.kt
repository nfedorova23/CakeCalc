package com.nfedorova.cakecal.data.datasource.database

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.presentation.ui.MainActivity
import com.nfedorova.cakecal.data.datasource.UserDataSource
import com.nfedorova.cakecal.data.datasource.mapper.sha256
import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn
import com.nfedorova.cakecal.presentation.ui.register.LogInActivity

class UserDataSourceImpl(private val context: Context) : UserDataSource {



    private val sharedPreferences = context.getSharedPreferences("UserId", Context.MODE_PRIVATE).edit()
    private val shPr = context.getSharedPreferences("Name", Context.MODE_PRIVATE).edit()
    override fun addDB(userDBO: UserDBO, change: ChangeOfActivitySignIn): Boolean {
        val user = hashMapOf(
            "name" to userDBO.name.text.toString(),
            "email" to userDBO.email.text.toString(),
            "password" to sha256(userDBO.password.text.toString())
        )

        Firebase.firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                shPr.putString("Name", userDBO.name.text.toString()).apply()
                change.changeOfActivity()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
        return true
    }

    override fun checkData(loginUserDBO: LoginUserDBO, change: ChangeOfActivityLogIn): Boolean {
        Firebase.firestore.collection("users")
            .get()
            .addOnSuccessListener {result ->
                for (document in result){
                    if (document.getString("email") == loginUserDBO.email.text.toString()){
                        if (document.getString("password") == sha256(loginUserDBO.password.text.toString())){
                           val id =  document.id
                            sharedPreferences.putString("UserId", id).apply()
                            change.changeOfActivity()
                        }
                    }
                }
            }
            .addOnFailureListener{
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
        return true
    }

    override fun logOut(change: ChangeOfActivityLogOut): Boolean {
        if (sharedPreferences != null) {
                sharedPreferences.remove("KEY").apply()
                change.changeOfActivity()
            return true
            }
        return false
    }
}