package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.repository.IngredientsRepository
import com.nfedorova.cakecal.domain.utils.TransferIngredients

class GetIngredientsUseCase(private val ingredientsRepository: IngredientsRepository) {
    fun execute(stringId: String, data: TransferIngredients): Boolean {
        return ingredientsRepository.getIngredients(stringId = stringId, data = data)
    }
}