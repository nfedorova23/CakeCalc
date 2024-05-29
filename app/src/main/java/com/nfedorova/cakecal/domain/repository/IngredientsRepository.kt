package com.nfedorova.cakecal.domain.repository

import com.nfedorova.cakecal.domain.utils.Transition

interface IngredientsRepository {
    fun getIngredients(stringId: String, data: Transition) : Boolean
}