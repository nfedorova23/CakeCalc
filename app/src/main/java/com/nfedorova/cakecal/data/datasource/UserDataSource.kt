package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn


interface UserDataSource {

    suspend fun addDB(userDBO: UserDBO, change: ChangeOfActivitySignIn) : Boolean
    suspend fun checkData(loginUserDBO: LoginUserDBO, change: ChangeOfActivityLogIn) : Boolean
    suspend fun logOut(change: ChangeOfActivityLogOut): Boolean
}