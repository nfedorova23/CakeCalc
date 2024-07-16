package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.domain.utils.TransferIngredients

interface IngredientsDataSource {
    suspend fun getIngredients(stringId: String, data: TransferIngredients) : Boolean
}