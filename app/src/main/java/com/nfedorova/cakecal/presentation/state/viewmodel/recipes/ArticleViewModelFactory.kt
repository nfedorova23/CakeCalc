package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.GetRecipesArticleUseCase

class ArticleViewModelFactory(context: Context?) : ViewModelProvider.Factory{

    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }
    private val articleUseCase by lazy {
        recipesRepository?.let {
            GetRecipesArticleUseCase(recipesRepository = it)
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return articleUseCase?.let { ArticleViewModel(articleUseCase = it) } as T
    }
}