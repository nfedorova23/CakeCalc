package com.nfedorova.cakecal.data.datasource

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.data.datasource.model.ArticleDBO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.data.datasource.model.RecipesDBO
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.domain.utils.TransferSaved


interface RecipeDataSource {

    fun addRecipe(recipeDBO: RecipesDBO, ingredientsList: MutableList<IngredientsDBO>): Boolean
    fun addRecipeToSaved(data: TransferSaved): Boolean
    fun getAllRecipes(data: TransferRecipes): Boolean
    fun getRecipeArticle(stringId: String, data: TransferArticle, model: ArticleDBO) : Boolean
}