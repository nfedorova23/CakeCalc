package com.nfedorova.cakecal.data.datasource.database

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter
import com.nfedorova.cakecal.presentation.state.adapter.RecipesAdapter
import com.nfedorova.cakecal.presentation.state.adapter.SavedAdapter

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

    override fun addRecipeToSaved(recyclerView: RecyclerView): MutableList<RecipeModelDBO> {
        val savedList = mutableListOf<RecipeModelDBO>()
        Firebase.firestore.collection("saved_recipes")
            .get()
            .addOnSuccessListener {
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModelDBO(recipeId,recipeTitle,recipeDescription)
                    savedList.add(recipe)
                    recyclerView.adapter = SavedAdapter(mapToRecipeModel(savedList))
                }
            }
        return savedList
    }

    override fun getAllRecipes(recyclerView: RecyclerView): MutableList<RecipeModelDBO>{
        val recipeList = mutableListOf<RecipeModelDBO>()
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.get()
            .addOnSuccessListener {
                for (document in it){
                    val recipeId = document.id
                    val recipeTitle = document.getString("title")
                    val recipeDescription = document.getString("description")
                    val recipe = RecipeModelDBO(recipeId,recipeTitle,recipeDescription)
                    recipeList.add(recipe)
                    recyclerView.adapter = RecipesAdapter(mapToRecipeModel(recipeList))
                }
            }
        return recipeList
    }

    override fun getRecipeArticle(stringId: String, recyclerView: RecyclerView, model: ArticleDBO) : Boolean{
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
                        recyclerView.adapter = ArticleAdapter(mapToIngredients(ingrList))
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