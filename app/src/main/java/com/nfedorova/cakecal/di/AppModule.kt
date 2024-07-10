package com.nfedorova.cakecal.di

import com.nfedorova.cakecal.presentation.state.viewmodel.calculate.CalculateViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.login.LogInViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AboutRecipesViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AddRecipesViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.RecipesViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.saved.SavedViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<SavedViewModel>{
        SavedViewModel(addRecipeToSavedUseCase = get())
    }

    viewModel<SignInViewModel>{
        SignInViewModel(registrationByEmailUseCase = get())
    }

    viewModel<LogInViewModel>{
        LogInViewModel(checkRegistrationByEmailUseCase = get())
    }

    viewModel<RecipesViewModel>{
        RecipesViewModel(getRecipesUseCase = get())
    }

    viewModel<ArticleViewModel>{
        ArticleViewModel(articleUseCase = get())
    }

    viewModel<AddRecipesViewModel>{
        AddRecipesViewModel(addNewRecipeUseCase = get())
    }

    viewModel<AboutRecipesViewModel>{
        AboutRecipesViewModel(logoutUseCase = get())
    }

    viewModel<CalculateViewModel>{
        CalculateViewModel(ingredientsUseCase = get())
    }
}