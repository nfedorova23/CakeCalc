package com.nfedorova.cakecal.data.datasource

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.data.datasource.model.ArticleDBO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.data.datasource.model.RecipesDBO



interface RecipeDataSource {

    fun addRecipe(recipeDBO: RecipesDBO, ingredientsList: MutableList<IngredientsDBO>): Boolean
    fun addRecipeToSaved(recyclerView: RecyclerView): MutableList<RecipeModelDBO>
    fun getAllRecipes(recyclerView: RecyclerView): MutableList<RecipeModelDBO>
    fun getRecipeArticle(stringId: String, recyclerView: RecyclerView, model: ArticleDBO) : Boolean
}