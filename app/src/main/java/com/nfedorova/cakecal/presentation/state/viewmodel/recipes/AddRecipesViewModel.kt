package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.usecase.AddNewRecipeUseCase
import kotlinx.coroutines.launch

class AddRecipesViewModel(
    private val addNewRecipeUseCase: AddNewRecipeUseCase
) : ViewModel() {

    fun addRecipes(recipe: Recipes, ingredientsList: MutableList<Ingredients>, view: View
    ) {
        viewModelScope.launch {
            addNewRecipeUseCase.execute(recipe = recipe, ingredientsList = ingredientsList)
            view.findNavController().navigate(R.id.action_addRecipesFragment_to_recipes_menu)
        }
    }
}