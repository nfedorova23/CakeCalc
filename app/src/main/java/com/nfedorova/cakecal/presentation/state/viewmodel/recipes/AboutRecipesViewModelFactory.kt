package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.LogoutUseCase

class AboutRecipesViewModelFactory(context: Context?) : ViewModelProvider.Factory {

    private val userRepository by lazy {
        context?.let { UserDataSourceImpl(context = it) }?.let { UserRepositoryImpl(dataSource = it) } }
    private val logoutUseCase by lazy { userRepository?.let { LogoutUseCase(userRepository = it) } }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return logoutUseCase?.let { AboutRecipesViewModel(logoutUseCase = it) } as T
    }
}