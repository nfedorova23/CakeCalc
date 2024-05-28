package com.nfedorova.cakecal.presentation.state.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.model.Ingredients


class IngredientsAdapter(private val ingredients: MutableList<Ingredients>):
    RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = ingredients[position]
        with(holder){
            ingredientsEditText.setText(item.ingredient)
            countEditText.setText(item.count)

            ingredientsEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    item.ingredient = ingredientsEditText.text.toString()
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            countEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    item.count = countEditText.text.toString()
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            deleteButton.setOnClickListener {
                ingredients.removeAt(position)
                notifyItemRemoved(position)
            }

        }
    }

    fun addEmptyNewItem(){
        ingredients.add(Ingredients("", ""))
        notifyItemInserted(ingredients.size - 1)
    }

    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ingredientsEditText = itemView.findViewById<EditText>(R.id.name_editText)
        val countEditText = itemView.findViewById<EditText>(R.id.count_editText)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.imageButton)

    }
}
