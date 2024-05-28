package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.repository.UserRepository

//exit
class LogoutUseCase(private val userRepository: UserRepository) {
    fun execute() : Boolean{
        return userRepository.logOut()
    }
}