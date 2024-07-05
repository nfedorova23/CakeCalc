package com.nfedorova.cakecal.presentation.state.viewmodel.saved

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.AddRecipeToSavedUseCase

class SavedViewModelFactory(context: Context?) : ViewModelProvider.Factory {

    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }

    private val addRecipeToSavedUseCase by lazy {
        recipesRepository?.let { AddRecipeToSavedUseCase(recipesRepository = it) }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return addRecipeToSavedUseCase?.let { SavedViewModel(addRecipeToSavedUseCase = it) } as T
    }
}