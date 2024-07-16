package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.usecase.GetRecipesArticleUseCase
import com.nfedorova.cakecal.domain.utils.TransferArticle
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val articleUseCase: GetRecipesArticleUseCase
) : ViewModel() {

    fun calculate(view : View, arguments: Bundle){
        view.findNavController().navigate(R.id.action_articleFragment_to_calculateFragment, arguments)
    }

    fun getArticle(arguments: Bundle, title: TextView, description: TextView, steps: TextView, data: TransferArticle) {
        viewModelScope.launch {
            val stringId = arguments.getString("id") ?: return@launch
            val article = Article(title = title, description = description, steps = steps)
            articleUseCase.execute(stringId = stringId, data = data, model = article)
        }
    }
}