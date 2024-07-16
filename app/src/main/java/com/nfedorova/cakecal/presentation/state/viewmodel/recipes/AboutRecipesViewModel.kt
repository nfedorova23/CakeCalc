package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfedorova.cakecal.domain.usecase.LogoutUseCase
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AboutRecipesViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel(){

    fun logOut(change: ChangeOfActivityLogOut) {
        viewModelScope.launch {
            logoutUseCase.execute(change = change)
        }
    }
}