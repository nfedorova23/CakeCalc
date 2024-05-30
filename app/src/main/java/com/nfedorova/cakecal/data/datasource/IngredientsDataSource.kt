package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.domain.utils.TransferIngredients

interface IngredientsDataSource {
    fun getIngredients(stringId: String, data: TransferIngredients) : Boolean
}