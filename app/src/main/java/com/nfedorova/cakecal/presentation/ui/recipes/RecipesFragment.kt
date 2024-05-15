package com.nfedorova.cakecal.presentation.ui.recipes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.databinding.FragmentRecipesBinding
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.presentation.RecipesPresenter
import com.nfedorova.cakecal.presentation.state.adapter.RecipesAdapter


class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: RecipesAdapter = RecipesAdapter(mutableListOf())
    private var recipesList = mutableListOf<RecipeModel>()
   // private val recipesPresenter = RecipesPresenter(this, RecipesRepository())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = activity?.getSharedPreferences("RI", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("RI", "9")?.apply()
        recyclerView = binding.rvRecipes
        val addFAB = binding.floatingActionButton

        makeAdapter()

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

        addFAB.setOnClickListener{
            it.findNavController().navigate(R.id.action_recipes_menu_to_addRecipesFragment)
        }

        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.get()
            .addOnSuccessListener {
                val recipeList = ArrayList<RecipeModel>()
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModel(recipeId,recipeTitle,recipeDescription)
                    recipeList.add(recipe)
                    recyclerView.adapter = RecipesAdapter(recipeList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
    }


    private fun makeAdapter() {
        adapter = RecipesAdapter(recipesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}