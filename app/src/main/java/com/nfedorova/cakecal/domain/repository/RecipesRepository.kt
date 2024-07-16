package com.nfedorova.cakecal.domain.repository

import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.domain.utils.TransferSaved


interface RecipesRepository {

    suspend fun addRecipe(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean?
    suspend fun addRecipeToSaved(data: TransferSaved, sp: SharedPreferences) : Boolean
    suspend fun getAllRecipes(data: TransferRecipes) : Boolean
    suspend fun getRecipeArticle(stringId: String, data: TransferArticle, model: Article) : Boolean


}