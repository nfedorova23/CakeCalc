package com.nfedorova.cakecal.presentation.state.viewmodel.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.nfedorova.cakecal.domain.model.Calculate
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.utils.CalculatingImpl
import com.nfedorova.cakecal.domain.utils.TransferIngredients
import com.nfedorova.cakecal.presentation.state.utils.validate

class CalculateViewModel(
    private val ingredientsUseCase: GetIngredientsUseCase
) : ViewModel(){

    private val calculating by lazy { CalculatingImpl() }

    fun showTable(context: Context){
        calculating.showTable(context)
    }

    fun getIngredients(arguments: Bundle, data: TransferIngredients){
        val stringId = arguments.getString("id") ?: return
        ingredientsUseCase.execute(stringId = stringId, data = data)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun calculate(calculate: Calculate){

        val ratio: Double
        with(calculate) {
            val dlOneED = validate(string = dH1ED.text.toString())
            val dlTwoED = validate(string = dH2ED.text.toString())
            val widthOne = validate(string = width1ED.text.toString())
            val widthTwo = validate(string = width2ED.text.toString())

            ratio = calculating.getRatio(
                itemOne = spinnerOne.selectedItem.toString(),
                itemTwo = spinnerTwo.selectedItem.toString(),
                dlOne = dlOneED, dlTwo = dlTwoED,
                wOne = widthOne, wTwo = widthTwo
            )
            val roundRatio = String.format("%.1f", ratio).replace(",", ".").toDouble()

            for (i in 0 until ingredientsList.size) {
                val l = ingredientsList[i]
                val list = Ingredients(
                    ingredient = l.ingredient,
                    count = ((l.count?.toInt())?.times(roundRatio)).toString()
                )
                ingredientsList[i] = list
                adapter.notifyDataSetChanged()
            }
            button.isEnabled = false
        }
    }
}
