package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.repository.UserRepository
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn



class CheckRegistrationByEmailUseCase(private val userRepository: UserRepository) {
    fun execute(user: LoginUser, change: ChangeOfActivityLogIn): Boolean {
        return userRepository.checkUserData(user = user, change = change)
    }
}