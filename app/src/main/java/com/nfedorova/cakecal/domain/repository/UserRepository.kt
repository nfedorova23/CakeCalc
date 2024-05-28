package com.nfedorova.cakecal.domain.repository

import android.content.SharedPreferences
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.User

interface UserRepository {

    fun addDBUserData(userDB: User) : Boolean
    fun checkUserData(user: LoginUser) : Boolean
    fun logOut(): Boolean
}