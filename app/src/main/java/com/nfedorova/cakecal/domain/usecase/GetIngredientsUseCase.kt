package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.repository.IngredientsRepository
import com.nfedorova.cakecal.presentation.state.Transition

class GetIngredientsUseCase(private val ingredientsRepository: IngredientsRepository) {
     fun execute(stringId: String, data: Transition): Boolean{
        return ingredientsRepository.getIngredients(stringId = stringId, data = data)
    }
}