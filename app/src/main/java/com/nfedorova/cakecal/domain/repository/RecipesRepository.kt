package com.nfedorova.cakecal.domain.repository

import com.nfedorova.cakecal.data.model.Recipes

interface RecipesRepository {
    suspend fun getAll(): List<Recipes>
    suspend fun findRecipes(title: String)

}