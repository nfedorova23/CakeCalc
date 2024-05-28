package com.nfedorova.cakecal.data.datasource

import com.nfedorova.cakecal.presentation.state.Transition

interface IngredientsDataSource {
    fun getIngredients(stringId: String, data: Transition) : Boolean
}