package com.nfedorova.cakecal.presentation.state.viewmodel.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.RegisterByEmailUseCase
import com.nfedorova.cakecal.presentation.state.viewmodel.login.LogInViewModel

class SignInViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy { UserRepositoryImpl(dataSource = UserDataSourceImpl(context = context)) }
    private val registrationByEmailUseCase by lazy {
        RegisterByEmailUseCase(
            userRepository = userRepository,
            context = context
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(registrationByEmailUseCase = registrationByEmailUseCase) as T
    }
}