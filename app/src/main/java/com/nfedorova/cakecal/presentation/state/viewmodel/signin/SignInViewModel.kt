package com.nfedorova.cakecal.presentation.state.viewmodel.signin

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.User
import com.nfedorova.cakecal.domain.usecase.RegisterByEmailUseCase
import com.nfedorova.cakecal.domain.utils.ChangeOfActivitySignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val registrationByEmailUseCase: RegisterByEmailUseCase
) : ViewModel(){
     fun register(name: TextView, email: TextView, pass: TextView, change: ChangeOfActivitySignIn) {
         viewModelScope.launch {
             val user = User(name = name, email = email, password = pass)
             registrationByEmailUseCase.execute(user = user, change = change)
         }
     }
}