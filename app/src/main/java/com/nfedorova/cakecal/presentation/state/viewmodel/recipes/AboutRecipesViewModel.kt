package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import androidx.lifecycle.ViewModel
import com.nfedorova.cakecal.domain.usecase.LogoutUseCase
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut

class AboutRecipesViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel(){

    fun logOut(change: ChangeOfActivityLogOut){
        logoutUseCase.execute(change = change)
    }
}