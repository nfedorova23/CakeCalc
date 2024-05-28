package com.nfedorova.cakecal.presentation.state

import com.nfedorova.cakecal.domain.model.Ingredients

interface Transition {
    fun transferData(list: ArrayList<Ingredients>)
}