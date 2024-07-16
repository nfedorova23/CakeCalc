package com.nfedorova.cakecal.domain.repository

import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn

interface UserRepository {

    suspend fun addDBUserData(userDB: User, change: ChangeOfActivitySignIn) : Boolean
    suspend fun checkUserData(user: LoginUser, change: ChangeOfActivityLogIn) : Boolean
    suspend fun logOut(change: ChangeOfActivityLogOut): Boolean
}