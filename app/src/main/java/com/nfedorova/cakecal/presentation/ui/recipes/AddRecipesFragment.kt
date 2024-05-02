package com.nfedorova.cakecal.presentation.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.databinding.FragmentAddBinding
import com.nfedorova.cakecal.data.model.Ingredients
import com.nfedorova.cakecal.presentation.state.adapter.IngredientsAdapter

class AddRecipesFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: IngredientsAdapter = IngredientsAdapter(mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)

        recyclerView = binding.recyclerView
        makeAdapter()


        binding.addIngredients.setOnClickListener {
            adapter.addEmptyNewItem()
        }

        binding.addButton.setOnClickListener {
            adapter
            addRecipe()
            it.findNavController().navigate(R.id.action_addRecipesFragment_to_recipes_menu)
        }

        return binding.root
    }



    private fun addRecipe(){
        val title: TextView = binding.titleEditText
        val description: TextView = binding.descriptionEditText
        val steps: TextView = binding.stepsEditText
        val recipeCollection = FirebaseFirestore.getInstance().collection("recipes")
        val recipeData = hashMapOf(
            "title" to title.text.toString(),
            "description" to description.text.toString(),
            "steps" to steps.text.toString()
        )
        recipeCollection.add(recipeData)
            .addOnSuccessListener {
                val recipeId = it.id

                val ingredientsCollection = FirebaseFirestore.getInstance()
                    .collection("recipes").document(recipeId)
                    .collection("ingredients")

                for (ingr in ingredientsList) {
                    val ingredientsData = hashMapOf(
                        "ingredient" to ingr.ingredient,
                        "count" to ingr.count
                    )
                    ingredientsCollection.add(ingredientsData)
                        //убрать потом этот блок кода
                        .addOnSuccessListener {
                            Toast.makeText(context, "Успех!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener{
                            Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
    }

   private fun makeAdapter() {
        adapter = IngredientsAdapter(ingredientsList)
        with(binding) {
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

}