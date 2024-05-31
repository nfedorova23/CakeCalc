package com.nfedorova.cakecal.presentation.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.databinding.FragmentArticleBinding
import com.nfedorova.cakecal.domain.usecase.GetRecipesArticleUseCase
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter


class ArticleFragment : Fragment(), TransferArticle{

    private lateinit var binding: FragmentArticleBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: ArticleAdapter = ArticleAdapter(ingredients = mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()
    private val recipesRepository by lazy { context?.let { RecipesDataSourceImpl(context = it) }
        ?.let { RecipesRepositoryImpl(recipeDataSource = it) } }
    private val articleUseCase by lazy { recipesRepository?.let {
        GetRecipesArticleUseCase(recipesRepository = it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        adapter = ArticleAdapter(ingredients = ingredientsList)
        recyclerView = binding.rvIngredients
        val calculate = binding.calculate

        calculate.setOnClickListener {
            it.findNavController().navigate(R.id.action_articleFragment_to_calculateFragment, arguments)
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
        val stringId = arguments?.getString("id") ?: return
        val article = Article(title = title, description = description, steps = steps)
        articleUseCase?.execute(stringId = stringId, data = this, model = article)
    }

    override fun transferData(list: MutableList<Ingredients>) {
        ingredientsList = list
        recyclerView.adapter = ArticleAdapter(ingredientsList)
    }

    companion object{
        fun newInstance(bundle: Bundle) = ArticleFragment().apply {
            arguments = bundle
        }
    }
}