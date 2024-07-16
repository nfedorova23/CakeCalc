package com.nfedorova.cakecal.presentation.state.viewmodel.saved

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfedorova.cakecal.domain.usecase.AddRecipeToSavedUseCase
import com.nfedorova.cakecal.domain.utils.TransferSaved
import kotlinx.coroutines.launch

class SavedViewModel(
    private val addRecipeToSavedUseCase: AddRecipeToSavedUseCase
) : ViewModel() {

    fun addRecipesToSaved(data: TransferSaved, sp: SharedPreferences) {
        viewModelScope.launch {
            addRecipeToSavedUseCase.execute(data = data, sp = sp)
        }
    }
}