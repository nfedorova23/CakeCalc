package com.nfedorova.cakecal.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentArticleBinding
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.usecase.GetRecipesArticleUseCase
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AboutRecipesViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AboutRecipesViewModelFactory
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleFragment : Fragment(), TransferArticle {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: ArticleAdapter = ArticleAdapter(ingredients = mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()
    private val viewModel by viewModel<ArticleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        adapter = ArticleAdapter(ingredients = ingredientsList)
        recyclerView = binding.rvIngredients
        val calculate = binding.calculate
        calculate.setOnClickListener {view ->
            arguments?.let { viewModel.calculate(view = view, arguments = it) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = binding.titleTv
        val description = binding.descriptionTv
        val steps = binding.stepsTv
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }
        arguments?.let { viewModel.getArticle(arguments = it, title = title, description = description, steps = steps, data = this) }
    }

    override fun transferData(list: MutableList<Ingredients>) {
        ingredientsList = list
        recyclerView.adapter = ArticleAdapter(ingredientsList)
    }

    companion object {
        fun newInstance(bundle: Bundle) = ArticleFragment().apply {
            arguments = bundle
        }
    }
}