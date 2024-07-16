package com.nfedorova.cakecal.presentation.state.viewmodel.login

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.usecase.CheckRegistrationByEmailUseCase
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogIn
import kotlinx.coroutines.launch

class LogInViewModel(
    private val checkRegistrationByEmailUseCase: CheckRegistrationByEmailUseCase
): ViewModel() {

    fun checkRegister(email: TextView, pass: TextView, change: ChangeOfActivityLogIn) {
        viewModelScope.launch {
            val user = LoginUser(email = email, password = pass)
            checkRegistrationByEmailUseCase.execute(user = user, change = change)
        }
    }
}