package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn


interface UserDataSource {

    fun addDB(userDBO: UserDBO, change: ChangeOfActivitySignIn) : Boolean
    fun checkData(loginUserDBO: LoginUserDBO, change: ChangeOfActivityLogIn) : Boolean
    fun logOut(change: ChangeOfActivityLogOut): Boolean

}