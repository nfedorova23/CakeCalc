package com.nfedorova.cakecal.domain.utils


import com.nfedorova.cakecal.domain.model.RecipeModel

interface TransferSaved {
    fun transferData(list: MutableList<RecipeModel>)
}