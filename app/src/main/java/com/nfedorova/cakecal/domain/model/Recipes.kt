package com.nfedorova.cakecal.domain.model

import com.nfedorova.cakecal.domain.model.Ingredients


data class Recipes(
    val id: String?,
    val title: String?,
    val description: String?,
    val ingredients: List<Ingredients> = mutableListOf(),
    val steps: String?

)






