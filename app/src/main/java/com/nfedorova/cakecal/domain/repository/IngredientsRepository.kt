package com.nfedorova.cakecal.domain.repository

import com.nfedorova.cakecal.domain.utils.TransferIngredients

interface IngredientsRepository {
    fun getIngredients(stringId: String, data: TransferIngredients) : Boolean
}