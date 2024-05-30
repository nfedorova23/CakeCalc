package com.nfedorova.cakecal.domain.usecase

import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.utils.TransferArticle

class GetRecipesArticleUseCase(private val recipesRepository: RecipesRepository) {
    fun execute(stringId: String, data: TransferArticle, model: Article): Boolean {
        return recipesRepository.getRecipeArticle(stringId = stringId, data = data, model = model)
    }
}