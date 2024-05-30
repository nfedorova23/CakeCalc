package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO


interface UserDataSource {

    fun addDB(userDBO: UserDBO) : Boolean
    fun checkData(loginUserDBO: LoginUserDBO) : Boolean
    fun logOut(): Boolean

}