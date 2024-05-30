package com.nfedorova.cakecal.data.repository


import com.nfedorova.cakecal.data.datasource.UserDataSource
import com.nfedorova.cakecal.data.datasource.mapper.mapToDBO
import com.nfedorova.cakecal.data.datasource.mapper.mapToLoginDBO
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.repository.UserRepository

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository{

    override fun addDBUserData(userDB: User) : Boolean {
        val userDBO = mapToDBO(userDB)
        return dataSource.addDB(userDBO = userDBO)
    }

    override fun checkUserData(user: LoginUser) : Boolean {
        val userDBO = mapToLoginDBO(user)
        return dataSource.checkData(loginUserDBO = userDBO)
    }

    override fun logOut(): Boolean {
        return dataSource.logOut()
    }
}

