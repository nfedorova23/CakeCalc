package com.nfedorova.cakecal.domain.model

data class RecipeModel(
    val id: String,
    val title: String?,
    val description: String?,
    var userId: String?
)
