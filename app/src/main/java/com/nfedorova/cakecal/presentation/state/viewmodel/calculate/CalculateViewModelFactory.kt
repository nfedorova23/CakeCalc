package com.nfedorova.cakecal.presentation.state.viewmodel.calculate

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nfedorova.cakecal.data.datasource.database.IngredientsDataSourceImpl
import com.nfedorova.cakecal.data.repository.IngredientsRepositoryImpl
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.utils.CalculatingImpl
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModel

class CalculateViewModelFactory(context: Context?) : ViewModelProvider.Factory {

    private val ingredientsRepository by lazy {
        context?.let { IngredientsDataSourceImpl() }
            ?.let { IngredientsRepositoryImpl(ingredientsDataSource = it) }
    }
    private val ingredientsUseCase by lazy {
        ingredientsRepository?.let {
            GetIngredientsUseCase(
                ingredientsRepository = it
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ingredientsUseCase?.let { CalculateViewModel(ingredientsUseCase = it) } as T
    }

}