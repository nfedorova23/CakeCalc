package com.nfedorova.cakecal.presentation.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.presentation.ui.settings.SettingsFragment


class RecipesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    companion object {
        const val TAG_RECIPES = "TAG_RECIPES"
        fun newInstance() = RecipesFragment()
       /* fun newInstance(param1: String, param2: String) =
            RecipesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}