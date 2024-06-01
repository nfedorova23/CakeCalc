package com.nfedorova.cakecal.domain.usecase

import android.content.SharedPreferences
import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.utils.TransferSaved

class AddRecipeToSavedUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(data: TransferSaved, sp: SharedPreferences): Boolean {
        return recipesRepository.addRecipeToSaved(data = data, sp = sp)
    }
}