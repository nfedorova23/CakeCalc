package com.nfedorova.cakecal.data.datasource.database

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nfedorova.cakecal.data.datasource.RecipeDataSource
import com.nfedorova.cakecal.data.datasource.mapper.mapToIngredients
import com.nfedorova.cakecal.data.datasource.mapper.mapToRecipeModel
import com.nfedorova.cakecal.data.datasource.model.ArticleDBO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.data.datasource.model.RecipesDBO
import com.nfedorova.cakecal.domain.utils.TransferArticle
import com.nfedorova.cakecal.domain.utils.TransferRecipes
import com.nfedorova.cakecal.domain.utils.TransferSaved

class RecipesDataSourceImpl(private val context: Context): RecipeDataSource {
    override fun addRecipe(recipeDBO: RecipesDBO, ingredientsList: MutableList<IngredientsDBO>): Boolean {
        val recipeCollection = FirebaseFirestore.getInstance().collection("recipes")
        val recipeData = hashMapOf(
            "title" to recipeDBO.title,
            "description" to recipeDBO.description,
            "steps" to recipeDBO.steps
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
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ошибка! Попробуйте еще раз", Toast.LENGTH_SHORT).show()
            }
        return true
    }

    override fun addRecipeToSaved(data: TransferSaved, sp: SharedPreferences): Boolean {
        val savedList = mutableListOf<RecipeModelDBO>()
        val id = sp.getString("UserId","" )
        Firebase.firestore.collection("saved_recipes")
            .whereEqualTo("userId", id)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModelDBO(recipeId,recipeTitle,recipeDescription, userId = null)
                    savedList.add(recipe)
                    data.transferData(mapToRecipeModel(savedList))
                }
            }
        return true
    }

    override fun getAllRecipes(data: TransferRecipes): Boolean{
        val recipeList = mutableListOf<RecipeModelDBO>()
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.get()
            .addOnSuccessListener {
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModelDBO(recipeId,recipeTitle,recipeDescription, userId = null)
                    recipeList.add(recipe)
                    data.transferData(mapToRecipeModel(recipeList))
                }
            }
        return true
    }

    override fun getRecipeArticle(stringId: String, data: TransferArticle, model: ArticleDBO) : Boolean{
        val articleList = mutableListOf<RecipesDBO>()
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.document(stringId)
            .get()
            .addOnSuccessListener {document ->
                val id = document.id
                val titleI = document.getString("title")
                val descriptionI = document.getString("description")
                val stepsI = document.getString("steps")
                val ingrList = ArrayList<IngredientsDBO>()
                val recipeRef = recipesRef.document(id)
                val ingrRef = recipeRef.collection("ingredients")
                ingrRef.get()
                    .addOnSuccessListener {ingredients ->
                        for (ingr in ingredients){
                            val i = ingr.getString("ingredient")
                            val c = ingr.getString("count")
                            val list = IngredientsDBO(i,c)
                            ingrList.add(list)
                        }
                        val article = RecipesDBO(id, titleI, descriptionI, ingrList, stepsI)
                        articleList.add(article)
                        data.transferData(mapToIngredients(ingrList))
                        with(model) {
                            title.text = titleI
                            description.text = descriptionI
                            steps.text = stepsI
                        }
                    }
            }
        return true
    }
}