package com.nfedorova.cakecal.domain.utils

import com.nfedorova.cakecal.domain.model.Ingredients

interface TransferArticle {
    fun transferData(list: MutableList<Ingredients>)
}