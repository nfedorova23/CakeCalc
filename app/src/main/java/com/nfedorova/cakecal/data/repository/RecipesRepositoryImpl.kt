package com.nfedorova.cakecal.data.repository

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

class RecipesRepositoryImpl(private val recipeDataSource: RecipeDataSource): RecipesRepository {
    override fun addRecipe(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean? {
        val recipeDBO = mapToRecipeDBO(recipe, ingredientsList)
        val ingredientsDBO = mapToIngredientsDBO(ingredientsList)
        return recipeDBO?.let { recipeDataSource.addRecipe(recipeDBO = it, ingredientsList = ingredientsDBO) }
    }

    override fun addRecipeToSaved(recyclerView: RecyclerView): MutableList<RecipeModel> {
        val dbo = recipeDataSource.addRecipeToSaved(recyclerView = recyclerView)
        return mapToRecipeModel(dbo)
    }

    override fun getAllRecipes(recyclerView: RecyclerView): MutableList<RecipeModel> {
        val dbo = recipeDataSource.getAllRecipes(recyclerView = recyclerView)
        return mapToRecipeModel(dbo)
    }

    override fun getRecipeArticle(stringId: String, recyclerView: RecyclerView, model: Article) : Boolean {
        return recipeDataSource.getRecipeArticle(stringId = stringId, recyclerView = recyclerView, model = mapToArticleDBO(model) )
    }

}