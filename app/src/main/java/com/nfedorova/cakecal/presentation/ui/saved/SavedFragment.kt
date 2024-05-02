package com.nfedorova.cakecal.presentation.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.databinding.FragmentSavedBinding
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.presentation.state.adapter.SavedAdapter


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: SavedAdapter = SavedAdapter(mutableListOf())
    private var recipesSavedList = mutableListOf<RecipeModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvRecipes
        makeAdapter()
        Firebase.firestore.collection("saved_recipes")
            .get()
            .addOnSuccessListener {
                val savedList = mutableListOf<RecipeModel>()
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModel(recipeId,recipeTitle,recipeDescription)
                    savedList.add(recipe)
                    recyclerView.adapter = SavedAdapter(savedList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }

        return binding.root
    }


    private fun makeAdapter() {
        adapter = SavedAdapter(recipesSavedList)
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