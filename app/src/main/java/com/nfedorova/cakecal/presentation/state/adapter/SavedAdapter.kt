package com.nfedorova.cakecal.presentation.state.adapter

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
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.domain.model.RecipeModel


class SavedAdapter(private val recipes: MutableList<RecipeModel>):
    RecyclerView.Adapter<SavedAdapter.SavedViewHolder>() {

    private val bundle: Bundle = Bundle()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SavedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipes, parent, false)
        return SavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener {
            bundle.putString("id", recipe.id)
            it.findNavController().navigate(R.id.action_saved_menu_to_articleFragment, bundle)
        }
        holder.itemView.findViewById<ImageButton>(R.id.imageButton).apply {
            this.setImageResource(R.drawable.baseline_favorite_24)
            this.setOnClickListener{
                this.setImageResource(R.drawable.baseline_favorite_border_24)
                Firebase.firestore.collection("saved_recipes").document(recipe.id)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }

    override fun getItemCount(): Int = recipes.size

   inner class SavedViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun bind(recipe: RecipeModel) {
            with(itemView) {
                findViewById<TextView>(R.id.title_tv).text = recipe.title
                findViewById<TextView>(R.id.description_tv).text =
                    recipe.description
            }
        }


    }
}