package com.nfedorova.cakecal.data.datasource.database

import com.google.firebase.firestore.FirebaseFirestore
import com.nfedorova.cakecal.data.datasource.IngredientsDataSource
import com.nfedorova.cakecal.data.datasource.mapper.mapToIngredientsDTO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.domain.utils.TransferIngredients

class IngredientsDataSourceImpl() : IngredientsDataSource {
    override fun getIngredients(stringId: String, data: TransferIngredients): Boolean {
        val ingredientsList = arrayListOf<IngredientsDBO>()
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.document(stringId)
            .get()
            .addOnSuccessListener { document ->
                val id = document.id
                val recipeRef = recipesRef.document(id)
                val ingrRef = recipeRef.collection("ingredients")
                ingrRef.get()
                    .addOnSuccessListener { ingredients ->
                        for (ingr in ingredients) {
                            val i = ingr.getString("ingredient")
                            val c = ingr.getString("count")
                            val list = IngredientsDBO(i, c)
                            ingredientsList.add(list)
                        }
                        data.transferData(mapToIngredientsDTO(ingredientsList))
                    }
            }
        return true
    }
}