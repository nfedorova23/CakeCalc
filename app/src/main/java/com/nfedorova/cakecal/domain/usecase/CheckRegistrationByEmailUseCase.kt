package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.repository.UserRepository


class CheckRegistrationByEmailUseCase(private val userRepository: UserRepository) {
    fun execute(user: LoginUser): Boolean {
        return userRepository.checkUserData(user = user)
    }
}