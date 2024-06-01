package com.nfedorova.cakecal.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentAddBinding
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.usecase.AddNewRecipeUseCase
import com.nfedorova.cakecal.presentation.state.adapter.IngredientsAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter

class AddRecipesFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: IngredientsAdapter = IngredientsAdapter(ingredients = mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()
    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }
    private val addNewRecipeUseCase by lazy {
        recipesRepository?.let {
            context?.let { it1 ->
                AddNewRecipeUseCase(
                    recipesRepository = it,
                    context = it1
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAddBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        adapter = IngredientsAdapter(ingredients = ingredientsList)
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }

        val title: TextView = binding.titleEditText
        val description: TextView = binding.descriptionEditText
        val steps: TextView = binding.stepsEditText
        binding.addIngredients.setOnClickListener {
            adapter.addEmptyNewItem()
        }

        binding.addButton.setOnClickListener {
            adapter
            val recipe = Recipes(
                id = id.toString(),
                title = title.text.toString(),
                description = description.text.toString(),
                ingredients = ingredientsList,
                steps = steps.text.toString()
            )
            addNewRecipeUseCase?.execute(recipe = recipe, ingredientsList = ingredientsList)
            it.findNavController().navigate(R.id.action_addRecipesFragment_to_recipes_menu)
        }
        return binding.root
    }
}