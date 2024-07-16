package com.nfedorova.cakecal.data.datasource

import android.content.SharedPreferences
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.data.datasource.model.ArticleDBO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.data.datasource.model.RecipesDBO
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.domain.utils.TransferSaved


interface RecipeDataSource {

    suspend fun addRecipe(recipeDBO: RecipesDBO, ingredientsList: MutableList<IngredientsDBO>): Boolean
    suspend fun addRecipeToSaved(data: TransferSaved, sp: SharedPreferences): Boolean
    suspend fun getAllRecipes(data: TransferRecipes): Boolean
    suspend fun getRecipeArticle(stringId: String, data: TransferArticle, model: ArticleDBO) : Boolean
}