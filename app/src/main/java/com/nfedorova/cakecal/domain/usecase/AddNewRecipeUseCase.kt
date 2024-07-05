package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.repository.RecipesRepository

class AddNewRecipeUseCase(private val recipesRepository: RecipesRepository, ) {
    fun execute(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean? {
        return recipesRepository.addRecipe(recipe = recipe, ingredientsList = ingredientsList)
    }
}