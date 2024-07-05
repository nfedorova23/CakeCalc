package com.nfedorova.cakecal.presentation.state.viewmodel.saved

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.nfedorova.cakecal.domain.usecase.AddRecipeToSavedUseCase
import com.nfedorova.cakecal.domain.utils.TransferSaved

class SavedViewModel(
    private val addRecipeToSavedUseCase: AddRecipeToSavedUseCase
) : ViewModel() {

    fun addRecipesToSaved(data: TransferSaved, sp: SharedPreferences){
        addRecipeToSavedUseCase.execute(data = data, sp = sp)
    }
}