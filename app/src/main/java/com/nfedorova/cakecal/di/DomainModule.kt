package com.nfedorova.cakecal.di

import com.nfedorova.cakecal.domain.usecase.AddNewRecipeUseCase
import com.nfedorova.cakecal.domain.usecase.AddRecipeToSavedUseCase
import com.nfedorova.cakecal.domain.usecase.CheckRegistrationByEmailUseCase
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.usecase.GetRecipesArticleUseCase
import com.nfedorova.cakecal.domain.usecase.GetRecipesUseCase
import com.nfedorova.cakecal.domain.usecase.LogoutUseCase
import com.nfedorova.cakecal.domain.usecase.RegisterByEmailUseCase
import org.koin.dsl.module



val domainModule = module {

    factory<LogoutUseCase> {
        LogoutUseCase(userRepository = get())
    }

    factory<CheckRegistrationByEmailUseCase> {
        CheckRegistrationByEmailUseCase(userRepository = get(), context = get())
    }

    factory<RegisterByEmailUseCase> {
        RegisterByEmailUseCase(userRepository = get(), context = get())
    }

    factory<GetIngredientsUseCase> {
        GetIngredientsUseCase(ingredientsRepository = get())
    }

    factory<AddNewRecipeUseCase> {
        AddNewRecipeUseCase(recipesRepository = get())
    }

    factory<GetRecipesArticleUseCase> {
        GetRecipesArticleUseCase(recipesRepository = get())
    }

    factory<GetRecipesUseCase> {
        GetRecipesUseCase(recipesRepository = get())
    }

    factory<AddRecipeToSavedUseCase> {
        AddRecipeToSavedUseCase(recipesRepository = get())
    }
}