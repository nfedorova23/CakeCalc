package com.nfedorova.cakecal.domain.utils

import com.nfedorova.cakecal.domain.model.Ingredients

interface TransferIngredients {
    fun transferData(list: ArrayList<Ingredients>)
}