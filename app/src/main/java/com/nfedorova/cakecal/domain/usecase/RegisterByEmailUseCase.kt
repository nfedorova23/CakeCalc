package com.nfedorova.cakecal.domain.usecase

import android.content.Context
import android.widget.Toast
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.repository.UserRepository
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn

class RegisterByEmailUseCase(
    private val userRepository: UserRepository,
    private val context: Context,
) {

    fun execute(user: User, change: ChangeOfActivitySignIn): Boolean {
        with(user) {
            if (name.text.isEmpty()) {
                Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
                return false
            }
            if (email.text.isEmpty() || !email.text.contains("@")) {
                Toast.makeText(context, "Почта введена неверно", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            if (password.text.isEmpty() || password.text.length < 6) {
                Toast.makeText(
                    context,
                    "Пароль должен быть больше 6 символов",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return false
            }
        }
        return userRepository.addDBUserData(userDB = user, change = change)
    }
}