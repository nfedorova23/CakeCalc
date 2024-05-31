package com.nfedorova.cakecal.data.repository


import com.nfedorova.cakecal.data.datasource.UserDataSource
import com.nfedorova.cakecal.data.datasource.mapper.mapToDBO
import com.nfedorova.cakecal.data.datasource.mapper.mapToLoginDBO
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.repository.UserRepository
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository{

    override fun addDBUserData(userDB: User, change: ChangeOfActivitySignIn) : Boolean {
        val userDBO = mapToDBO(userDB)
        return dataSource.addDB(userDBO = userDBO, change = change)
    }

    override fun checkUserData(user: LoginUser, change: ChangeOfActivityLogIn) : Boolean {
        val userDBO = mapToLoginDBO(user)
        return dataSource.checkData(loginUserDBO = userDBO, change = change)
    }

    override fun logOut(change: ChangeOfActivityLogOut): Boolean {
        return dataSource.logOut(change = change)
    }
}

