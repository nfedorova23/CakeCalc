package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.repository.UserRepository
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend fun execute(change: ChangeOfActivityLogOut): Boolean {
        return userRepository.logOut(change = change)
    }
}