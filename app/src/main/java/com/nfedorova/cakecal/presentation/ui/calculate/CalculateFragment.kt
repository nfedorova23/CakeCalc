package com.nfedorova.cakecal.presentation.ui.calculate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.IngredientsDataSourceImpl
import com.nfedorova.cakecal.data.repository.IngredientsRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentCalculateBinding
import com.nfedorova.cakecal.domain.model.Calculate
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.utils.CalculatingImpl
import com.nfedorova.cakecal.domain.utils.TransferIngredients
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter
import com.nfedorova.cakecal.presentation.state.utils.invisible
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter
import com.nfedorova.cakecal.presentation.state.utils.validate
import com.nfedorova.cakecal.presentation.state.utils.visible
import com.nfedorova.cakecal.presentation.state.viewmodel.calculate.CalculateViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.calculate.CalculateViewModelFactory
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModel
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.ArticleViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculateFragment : Fragment(), TransferIngredients {

    private lateinit var binding: FragmentCalculateBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private var ingredientsList = arrayListOf<Ingredients>()
    private val viewModel by viewModel<CalculateViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCalculateBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvCalculate
        adapter = ArticleAdapter(ingredientsList)
        recyclerView.adapter = adapter
        context?.let { makeAdapter(recyclerView = recyclerView, context = it) }
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.btnCalculate
        val showTableTV = binding.tvTable

        val dH1TV = binding.tvDH1
        val dH2TV = binding.tvDH2
        val dH1ED = binding.editTextDH1
        val dH2ED = binding.editTextDH2
        val width1TV = binding.tvWidth1
        val width2TV = binding.tvWidth2
        val width1ED = binding.editTextWidth1
        val width2ED = binding.editTextWidth2
        val spinnerOne = binding.spinner
        val spinnerTwo = binding.spinnerForm

        val textD = resources.getString(R.string.diameter)
        val textL = resources.getString(R.string.length)

        showTableTV.setOnClickListener {
            viewModel.showTable(it.context)
        }

        spinnerOne.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    when (parent.getItemAtPosition(position).toString()) {
                        "Round" -> {
                            invisible(tv = width1TV, ed = width1ED)
                            dH1TV.text = textD
                        }

                        "Rectangular" -> {
                            visible(tv = width1TV, ed = width1ED)
                            dH1TV.text = textL
                        }

                        "Square" -> {
                            invisible(tv = width1TV, ed = width1ED)
                            dH1TV.text = textL
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }

        spinnerTwo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    when (parent.getItemAtPosition(position).toString()) {
                        "Round" -> {
                            invisible(tv = width2TV, ed = width2ED)
                            dH2TV.text = textD
                        }

                        "Rectangular" -> {
                            visible(tv = width2TV, ed = width2ED)
                            dH2TV.text = textL
                        }

                        "Square" -> {
                            invisible(tv = width2TV, ed = width2ED)
                            dH2TV.text = textL
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        arguments?.let { viewModel.getIngredients(arguments = it, data = this) }
        button.setOnClickListener {
            val calculate = Calculate(dH1ED = dH1ED, dH2ED = dH2ED, width1ED = width1ED, width2ED = width2ED,
                spinnerOne = spinnerOne, spinnerTwo = spinnerTwo,
                ingredientsList = ingredientsList, adapter = adapter, button = button)
            viewModel.calculate(calculate)
        }
    }


    override fun transferData(list: ArrayList<Ingredients>) {
        ingredientsList = list
        adapter = ArticleAdapter(ingredients = ingredientsList)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(bundle: Bundle) = CalculateFragment().apply {
            arguments = bundle
        }
    }
}