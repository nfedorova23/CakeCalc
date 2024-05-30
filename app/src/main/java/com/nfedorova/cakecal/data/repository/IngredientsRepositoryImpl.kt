package com.nfedorova.cakecal.data.repository

import com.nfedorova.cakecal.data.datasource.IngredientsDataSource
import com.nfedorova.cakecal.domain.repository.IngredientsRepository
import com.nfedorova.cakecal.domain.utils.TransferIngredients

class IngredientsRepositoryImpl(private val ingredientsDataSource: IngredientsDataSource): IngredientsRepository {
    override fun getIngredients(stringId: String, data: TransferIngredients): Boolean {
        return ingredientsDataSource.getIngredients(stringId = stringId, data = data)
    }
}