package com.nfedorova.cakecal.di

import com.nfedorova.cakecal.data.datasource.IngredientsDataSource
import com.nfedorova.cakecal.data.datasource.RecipeDataSource
import com.nfedorova.cakecal.data.datasource.UserDataSource
import com.nfedorova.cakecal.data.datasource.database.IngredientsDataSourceImpl
import com.nfedorova.cakecal.data.datasource.database.RecipesDataSourceImpl
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.IngredientsRepositoryImpl
import com.nfedorova.cakecal.data.repository.RecipesRepositoryImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.domain.repository.IngredientsRepository
import com.nfedorova.cakecal.domain.repository.RecipesRepository
import com.nfedorova.cakecal.domain.repository.UserRepository
import org.koin.dsl.module


val dataModule = module {

    single<UserDataSource> {
        UserDataSourceImpl(context = get())
    }

    single<UserRepository>{
        UserRepositoryImpl(dataSource = get())
    }

    single<RecipeDataSource> {
        RecipesDataSourceImpl(context = get())
    }

    single<RecipesRepository>{
        RecipesRepositoryImpl(recipeDataSource = get())
    }

    single<IngredientsDataSource> {
        IngredientsDataSourceImpl()
    }

    single<IngredientsRepository>{
        IngredientsRepositoryImpl(ingredientsDataSource = get())
    }
}