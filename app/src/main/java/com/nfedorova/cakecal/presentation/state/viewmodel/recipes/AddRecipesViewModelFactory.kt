package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.AddNewRecipeUseCase

class AddRecipesViewModelFactory(context: Context?) : ViewModelProvider.Factory{
    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }
    private val addNewRecipeUseCase by lazy {
        recipesRepository?.let { AddNewRecipeUseCase(recipesRepository = it) }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return addNewRecipeUseCase?.let { AddRecipesViewModel(addNewRecipeUseCase = it) } as T
    }
}