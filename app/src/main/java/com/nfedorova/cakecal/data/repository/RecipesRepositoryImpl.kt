package com.nfedorova.cakecal.data.repository

import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.data.datasource.RecipeDataSource
import com.nfedorova.cakecal.data.datasource.mapper.mapToArticleDBO
import com.nfedorova.cakecal.data.datasource.mapper.mapToIngredientsDBO
import com.nfedorova.cakecal.data.datasource.mapper.mapToRecipeDBO
import com.nfedorova.cakecal.data.datasource.mapper.mapToRecipeModel
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.domain.utils.TransferSaved

class RecipesRepositoryImpl(private val recipeDataSource: RecipeDataSource): RecipesRepository {
    override suspend fun addRecipe(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean? {
        val recipeDBO = mapToRecipeDBO(recipe, ingredientsList)
        val ingredientsDBO = mapToIngredientsDBO(ingredientsList)
        return recipeDBO?.let { recipeDataSource.addRecipe(recipeDBO = it, ingredientsList = ingredientsDBO) }
    }

    override suspend fun addRecipeToSaved(data: TransferSaved, sp: SharedPreferences) : Boolean {
        return recipeDataSource.addRecipeToSaved(data = data, sp = sp)
    }

    override suspend fun getAllRecipes(data: TransferRecipes) : Boolean{
        return recipeDataSource.getAllRecipes(data = data)
    }

    override suspend fun getRecipeArticle(stringId: String, data: TransferArticle, model: Article) : Boolean  {
         return recipeDataSource.getRecipeArticle(stringId = stringId, data = data, model = mapToArticleDBO(model) )
    }

}