package com.nfedorova.cakecal.domain.usecase

import android.content.Context
import android.widget.Toast
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.repository.RecipesRepository

class AddNewRecipeUseCase(
    private val recipesRepository: RecipesRepository,
    private val context: Context,
) {
    fun execute(recipe: Recipes, ingredientsList: MutableList<Ingredients>): Boolean? {
        with(recipe) {
            if (title.isNullOrEmpty()) {
                Toast.makeText(context, "Введите название рецепта", Toast.LENGTH_SHORT).show()
                return false
            }
            if (description.isNullOrEmpty()) {
                Toast.makeText(context, "Введите описание рецепта", Toast.LENGTH_SHORT).show()
                return false
            }
            if (ingredientsList.isEmpty()) {
                Toast.makeText(context, "Введите ингредиенты", Toast.LENGTH_SHORT).show()
                return false
            }
            if (steps.isNullOrEmpty()) {
                Toast.makeText(context, "Введите шаги приготовления", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return recipesRepository.addRecipe(recipe = recipe, ingredientsList = ingredientsList)
    }
}