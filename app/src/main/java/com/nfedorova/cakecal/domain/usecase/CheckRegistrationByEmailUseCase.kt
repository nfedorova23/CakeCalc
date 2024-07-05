package com.nfedorova.cakecal.domain.usecase

import android.content.Context
import android.widget.Toast
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.repository.UserRepository
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn


class CheckRegistrationByEmailUseCase(private val userRepository: UserRepository, private val context: Context) {
    fun execute(user: LoginUser, change: ChangeOfActivityLogIn): Boolean {
        if (user.email.text.isNullOrEmpty() && user.password.text.isNullOrEmpty()){
            Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
        }
        return userRepository.checkUserData(user = user, change = change)
    }
}