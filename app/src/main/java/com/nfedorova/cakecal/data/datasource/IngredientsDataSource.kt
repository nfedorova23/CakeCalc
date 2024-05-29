package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.domain.utils.Transition

interface IngredientsDataSource {
    fun getIngredients(stringId: String, data: Transition) : Boolean
}