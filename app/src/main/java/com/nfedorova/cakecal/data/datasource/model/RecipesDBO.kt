package com.nfedorova.cakecal.data.datasource.model




data class RecipesDBO(
    val id: String,
    val title: String?,
    val description: String?,
    val ingredients: MutableList<IngredientsDBO> = mutableListOf(),
    val steps: String?
)
