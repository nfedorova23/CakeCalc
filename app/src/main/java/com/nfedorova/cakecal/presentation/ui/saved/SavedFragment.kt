package com.nfedorova.cakecal.presentation.ui.saved

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.databinding.FragmentSavedBinding
import com.nfedorova.cakecal.domain.model.RecipeModel
import com.nfedorova.cakecal.domain.utils.TransferSaved
import com.nfedorova.cakecal.presentation.state.adapter.SavedAdapter
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter
import com.nfedorova.cakecal.presentation.state.viewmodel.saved.SavedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SavedFragment : Fragment(), TransferSaved {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var recyclerView: RecyclerView
    private var adapter: SavedAdapter = SavedAdapter(recipes = mutableListOf())
    private var recipesSavedList = mutableListOf<RecipeModel>()
    private val viewModel by viewModel<SavedViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvRecipes
        adapter = SavedAdapter(recipes = recipesSavedList)
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }
        val sharedPreferences = activity?.getSharedPreferences("UserId", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            viewModel.addRecipesToSaved(this, sharedPreferences)
        }
        return binding.root
    }

    override fun transferData(list: MutableList<RecipeModel>) {
        recipesSavedList = list
        recyclerView.adapter = SavedAdapter(recipesSavedList)
    }
}