package com.nfedorova.cakecal.presentation.model

import com.nfedorova.cakecal.data.model.Ingredients
import com.nfedorova.cakecal.data.model.Recipes

interface RecipesView {
    fun addRecipes(recipes: Recipes, ingredients: Ingredients)
    fun addSaved(isSaved: Boolean, id: Int)
    fun deleteSaved(isSaved: Boolean, id: Int)
}