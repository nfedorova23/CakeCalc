package com.nfedorova.cakecal.domain.usecase

import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.repository.RecipesRepository

class GetRecipesArticleUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(stringId: String, recyclerView: RecyclerView, model: Article): Boolean {
        return recipesRepository.getRecipeArticle(stringId = stringId, recyclerView = recyclerView, model = model)
    }
}