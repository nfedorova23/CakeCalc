package com.nfedorova.cakecal.presentation.ui.recipes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentRecipesBinding
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.usecase.GetRecipesUseCase
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.presentation.state.adapter.RecipesAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter

class RecipesFragment : Fragment(), TransferRecipes {

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipesAdapter
    private var recipesList = mutableListOf<RecipeModel>()
    private val recipesRepository by lazy {
        context?.let { RecipesDataSourceImpl(context = it) }
            ?.let { RecipesRepositoryImpl(recipeDataSource = it) }
    }
    private val getRecipesUseCase by lazy {
        recipesRepository?.let {
            GetRecipesUseCase(
                recipesRepository = it
            )
        }
    }

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater)
        sharedPreferences = activity?.getSharedPreferences("UserId", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferencesP = activity?.getSharedPreferences("RI", Context.MODE_PRIVATE)
        sharedPreferencesP?.edit()?.putString("RI", "9")?.apply()
        recyclerView = binding.rvRecipes


        val addFAB = binding.floatingActionButton
        //val sharedPreferences = activity?.getSharedPreferences("KEY", Context.MODE_PRIVATE)
        adapter = RecipesAdapter(recipes = recipesList, sharedPreferences = sharedPreferences)
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }
        getRecipesUseCase?.execute(data = this)
        //adapter RecipesAdapter(recipes = mutableListOf(), )
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    addFAB.hide()
                } else {
                    addFAB.show()
                }
            }
        })

        addFAB.setOnClickListener {
            it.findNavController().navigate(R.id.action_recipes_menu_to_addRecipesFragment)
        }
    }

    override fun transferData(list: MutableList<RecipeModel>) {
        recipesList = list
        recyclerView.adapter =
            sharedPreferences?.let { RecipesAdapter(recipes = recipesList, sharedPreferences = it) }
    }
}