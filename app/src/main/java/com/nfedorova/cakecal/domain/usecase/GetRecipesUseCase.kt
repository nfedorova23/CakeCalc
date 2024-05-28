package com.nfedorova.cakecal.domain.usecase

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.repository.RecipesRepository

class GetRecipesUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(recyclerView: RecyclerView): MutableList<RecipeModel>{
        return recipesRepository.getAllRecipes(recyclerView = recyclerView)
    }
}