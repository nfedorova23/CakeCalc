package com.nfedorova.cakecal.domain.utils


import com.nfedorova.cakecal.domain.model.RecipeModel

interface TransferRecipes {
    fun transferData(list: MutableList<RecipeModel>)
}