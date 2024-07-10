package com.nfedorova.cakecal.presentation.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.databinding.FragmentAddBinding
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.presentation.state.adapter.IngredientsAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AddRecipesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddRecipesFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: IngredientsAdapter = IngredientsAdapter(ingredients = mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()
    private val viewModel by viewModel<AddRecipesViewModel>()

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
            if (title.text.isNullOrEmpty()) {
                Toast.makeText(context, "Введите название рецепта", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (description.text.isNullOrEmpty()) {
                Toast.makeText(context, "Введите описание рецепта", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ingredientsList.isEmpty()) {
                Toast.makeText(context, "Введите ингредиенты", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (steps.text.isNullOrEmpty()) {
                Toast.makeText(context, "Введите шаги приготовления", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.addRecipes(recipe = recipe, ingredientsList = ingredientsList, view = it)
        }
        return binding.root
    }
}