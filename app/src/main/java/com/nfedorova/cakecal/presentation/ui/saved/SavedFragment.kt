package com.nfedorova.cakecal.presentation.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentSavedBinding
import com.nfedorova.cakecal.domain.usecase.AddRecipeToSavedUseCase
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.utils.TransferSaved
import com.nfedorova.cakecal.presentation.state.adapter.SavedAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter


class SavedFragment : Fragment(), TransferSaved {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: SavedAdapter = SavedAdapter(recipes = mutableListOf())
    private var recipesSavedList = mutableListOf<RecipeModel>()
    private val recipesRepository by lazy { context?.let { RecipesDataSourceImpl(context = it) }
        ?.let { RecipesRepositoryImpl(recipeDataSource = it) } }
    private val addRecipeToSavedUseCase by lazy { recipesRepository?.let {
        AddRecipeToSavedUseCase(recipesRepository = it) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvRecipes
        adapter = SavedAdapter(recipes = recipesSavedList)
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }
        addRecipeToSavedUseCase?.execute(data = this)
        return binding.root
    }

    override fun transferData(list: MutableList<RecipeModel>) {
        recipesSavedList = list
        recyclerView.adapter = SavedAdapter(recipesSavedList)
    }
}