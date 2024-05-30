package com.nfedorova.cakecal.domain.usecase

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.utils.TransferSaved

class AddRecipeToSavedUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(data: TransferSaved): Boolean {
        return recipesRepository.addRecipeToSaved(data)
    }
}