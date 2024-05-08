package com.nfedorova.cakecal.data.model

import com.nfedorova.cakecal.data.model.Ingredients


data class Recipes(
    val id: String,
    val title: String?,
    val description: String?,
    val ingredients: List<Ingredients> = mutableListOf(),
    val steps: String?

)






