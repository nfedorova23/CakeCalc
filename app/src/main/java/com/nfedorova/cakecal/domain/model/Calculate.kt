package com.nfedorova.cakecal.domain.model

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter

data class Calculate(
    val dH1ED: EditText,
    val dH2ED: EditText,
    val width1ED: EditText,
    val width2ED: EditText,
    val spinnerOne: Spinner,
    val spinnerTwo: Spinner,
    val ingredientsList: ArrayList<Ingredients>,
    val adapter: ArticleAdapter,
    val button: Button
)
