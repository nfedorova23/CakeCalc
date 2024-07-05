package com.nfedorova.cakecal.presentation.state.viewmodel.recipes

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.domain.usecase.GetRecipesUseCase
import com.nfedorova.cakecal.domain.utils.TransferRecipes

class RecipesViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    fun onScrolled(recyclerView: RecyclerView, addFAB: FloatingActionButton){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    addFAB.hide()
                } else {
                    addFAB.show()
                }
            }
        })
    }

    fun getRecipes(data: TransferRecipes){
        getRecipesUseCase.execute(data = data)
    }

    fun addRecipes(addFAB: FloatingActionButton, view: View){
        view.findNavController().navigate(R.id.action_recipes_menu_to_addRecipesFragment)
    }
}