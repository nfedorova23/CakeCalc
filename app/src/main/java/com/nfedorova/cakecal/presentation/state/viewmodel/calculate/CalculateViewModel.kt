package com.nfedorova.cakecal.presentation.state.viewmodel.calculate

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nfedorova.cakecal.domain.model.Calculate
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.utils.CalculatingImpl
import com.nfedorova.cakecal.domain.utils.TransferIngredients
import com.nfedorova.cakecal.presentation.state.utils.validate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CalculateViewModel(
    private val ingredientsUseCase: GetIngredientsUseCase
) : ViewModel(){

    private val calculating by lazy { CalculatingImpl() }

    fun showTable(context: Context) {
        viewModelScope.launch {
            calculating.showTable(context)
        }
    }

    fun getIngredients(arguments: Bundle, data: TransferIngredients) {
        viewModelScope.launch {
            val stringId = arguments.getString("id") ?: return@launch
            ingredientsUseCase.execute(stringId = stringId, data = data)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun calculate(calculate: Calculate){
        viewModelScope.launch {
        val ratio: Double
        with(calculate) {
            val dlOneED = dH1ED.validate()
            val dlTwoED = dH2ED.validate()
            val widthOne = width1ED.validate()
            val widthTwo = width2ED.validate()

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
}
