package com.nfedorova.cakecal.presentation.state.adapter

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.model.RecipeModel


class RecipesAdapter(
    private val recipes: MutableList<RecipeModel>,
    private var sharedPreferences: SharedPreferences,
) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    private val bundle: Bundle = Bundle()
    private var userId: String = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecipesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipes, parent, false)
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener {
            bundle.putString("id", recipe.id)
            it.findNavController().navigate(R.id.action_recipesFragment_to_articleFragment, bundle)
        }
        holder.itemView.findViewById<ImageButton>(R.id.imageButton).apply {
            this.setOnClickListener {
                val id = sharedPreferences.getString("UserId", "")
                if (userId == id) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                } else {
                    recipe.userId = id
                    this.setImageResource(R.drawable.baseline_favorite_24)
                    Firebase.firestore.collection("saved_recipes").document(recipe.id)
                        .set(recipe)
                        .addOnSuccessListener {
                            Toast.makeText(context, "is Saved", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: RecipeModel) {
            with(itemView) {
                findViewById<TextView>(R.id.title_tv).text = recipe.title
                findViewById<TextView>(R.id.description_tv).text = recipe.description
            }
        }
    }
}


