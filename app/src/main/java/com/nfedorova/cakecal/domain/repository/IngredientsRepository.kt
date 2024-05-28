package com.nfedorova.cakecal.domain.repository

import com.nfedorova.cakecal.presentation.state.Transition

interface IngredientsRepository {
    fun getIngredients(stringId: String, data: Transition) : Boolean
}