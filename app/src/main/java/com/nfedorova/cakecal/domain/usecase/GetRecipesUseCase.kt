package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.utils.TransferRecipes

class GetRecipesUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(data: TransferRecipes): Boolean{
        return recipesRepository.getAllRecipes(data = data)
    }
}