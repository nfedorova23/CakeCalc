package com.nfedorova.cakecal.data.datasource.mapper

import com.nfedorova.cakecal.data.datasource.model.ArticleDBO
import com.nfedorova.cakecal.data.datasource.model.IngredientsDBO
import com.nfedorova.cakecal.data.datasource.model.LoginUserDBO
import com.nfedorova.cakecal.data.datasource.model.RecipeModelDBO
import com.nfedorova.cakecal.data.datasource.model.RecipesDBO
import com.nfedorova.cakecal.data.datasource.model.UserDBO
import com.nfedorova.cakecal.domain.model.Article
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.model.LoginUser
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.model.Recipes
import com.nfedorova.cakecal.domain.model.User

 fun mapToDBO(userDB: User) : UserDBO {
    return UserDBO(name = userDB.name, email = userDB.email, password = userDB.password )
} fun mapToLoginDBO(user: LoginUser): LoginUserDBO {
    return LoginUserDBO(email = user.email, password = user.password)
}

fun mapToRecipeDBO(recipe: Recipes, ingredientsList: MutableList<Ingredients>) : RecipesDBO? {
    return recipe.id?.let { RecipesDBO(
        id = it, title = recipe.title,
        description = recipe.description, ingredients = mapToIngredientsDBO(ingredientsList), steps = recipe.steps ) }
}

fun mapToIngredientsDBO(ingredientsList: MutableList<Ingredients>) : MutableList<IngredientsDBO>{
    val ingrDBO = mutableListOf<IngredientsDBO>()
    for (ingredient in ingredientsList){
        val dbo = IngredientsDBO(ingredient = ingredient.ingredient, count = ingredient.count)
        ingrDBO.add(dbo)
    }
    return ingrDBO
}
fun mapToRecipeModel(dbo: MutableList<RecipeModelDBO>): MutableList<RecipeModel>{
    val recipeModelList: MutableList<RecipeModel> = mutableListOf()
    for (list in dbo){
        val recipeModel = RecipeModel(id = list.id, title = list.title, description = list.description, userId = list.userId)
        recipeModelList.add(recipeModel)
    }
    return recipeModelList
}

fun mapToIngredients(dbo: MutableList<IngredientsDBO>): MutableList<Ingredients>{
    val ingredientsList: MutableList<Ingredients> = mutableListOf()
    for(list in dbo){
        val ingredients = Ingredients(ingredient = list.ingredient, count = list.count)
        ingredientsList.add(ingredients)
    }
    return ingredientsList
}


fun mapToArticleDBO(article: Article) : ArticleDBO {
    return ArticleDBO(title = article.title, description = article.description, steps = article.steps )
}

fun mapToIngredientsDTO(dbo: ArrayList<IngredientsDBO>): ArrayList<Ingredients>{
    val ingredientsList: ArrayList<Ingredients> = arrayListOf()
    for(list in dbo){
        val ingredients = Ingredients(ingredient = list.ingredient, count = list.count)
        ingredientsList.add(ingredients)
    }
    return ingredientsList
}
















