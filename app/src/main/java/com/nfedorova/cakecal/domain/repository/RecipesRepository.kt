package com.nfedorova.cakecal.domain.repository

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.model.Recipes


interface RecipesRepository {
    fun addRecipe(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean?
    fun addRecipeToSaved(recyclerView: RecyclerView): MutableList<RecipeModel>
    fun getAllRecipes(recyclerView: RecyclerView): MutableList<RecipeModel>
    fun getRecipeArticle(stringId: String, recyclerView: RecyclerView, model: Article) : Boolean


}