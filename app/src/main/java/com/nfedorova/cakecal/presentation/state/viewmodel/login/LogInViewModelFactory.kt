package com.nfedorova.cakecal.presentation.state.viewmodel.login


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.CheckRegistrationByEmailUseCase

class LogInViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy { UserRepositoryImpl(dataSource = UserDataSourceImpl(context = context)) }
    private val checkRegistrationByEmailUseCase by lazy {
        CheckRegistrationByEmailUseCase(
            userRepository = userRepository,
            context = context
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogInViewModel(checkRegistrationByEmailUseCase = checkRegistrationByEmailUseCase) as T
    }
}