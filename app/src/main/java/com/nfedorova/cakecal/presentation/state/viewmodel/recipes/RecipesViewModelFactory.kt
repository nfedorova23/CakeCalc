package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.GetRecipesUseCase

class RecipesViewModelFactory(context: Context?) : Factory {
    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }
    private val getRecipesUseCase by lazy {
        recipesRepository?.let {
            GetRecipesUseCase(
                recipesRepository = it
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return getRecipesUseCase?.let { RecipesViewModel(getRecipesUseCase = it) } as T
    }
}