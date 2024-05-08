package com.nfedorova.cakecal.presentation.state.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.model.Ingredients

class ArticleAdapter(private val ingredients: MutableList<Ingredients>):
RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArticleAdapter.ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_calculate, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int = ingredients.size

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(ingredients: Ingredients){
            with(itemView){
                findViewById<TextView>(R.id.tv_ingredients).text = ingredients.ingredient
                findViewById<TextView>(R.id.tv_count).text = ingredients.count
            }
        }
    }
}