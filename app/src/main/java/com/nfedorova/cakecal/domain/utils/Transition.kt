package com.nfedorova.cakecal.domain.utils

import com.nfedorova.cakecal.domain.model.Ingredients

interface Transition {
    fun transferData(list: ArrayList<Ingredients>)
}