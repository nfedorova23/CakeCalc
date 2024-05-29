package com.nfedorova.cakecal.presentation.ui.calculate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.datasource.database.IngredientsDataSourceImpl
import com.nfedorova.cakecal.data.repository.IngredientsRepositoryImpl
import com.nfedorova.cakecal.domain.model.Ingredients
import com.nfedorova.cakecal.databinding.FragmentCalculateBinding
import com.nfedorova.cakecal.domain.usecase.GetIngredientsUseCase
import com.nfedorova.cakecal.domain.utils.CalculatingImpl
import com.nfedorova.cakecal.domain.utils.Transition
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter
import com.nfedorova.cakecal.presentation.state.utils.invisible
import com.nfedorova.cakecal.presentation.state.utils.makeAdapter
import com.nfedorova.cakecal.presentation.state.utils.validate
import com.nfedorova.cakecal.presentation.state.utils.visible

class CalculateFragment : Fragment(), Transition {

    private lateinit var binding: FragmentCalculateBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private var ingredientsList = arrayListOf<Ingredients>()
    private val ingredientsRepository by lazy { context?.let { IngredientsDataSourceImpl() }
       ?.let { IngredientsRepositoryImpl(ingredientsDataSource = it) } }
    private val ingredientsUseCase by lazy { ingredientsRepository?.let { GetIngredientsUseCase(ingredientsRepository = it) } }
    private val calculating by lazy { CalculatingImpl() }

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

        val dH1TV = binding.tvDH1; val dH2TV = binding.tvDH2
        val dH1ED = binding.editTextDH1; val dH2ED = binding.editTextDH2
        val width1TV = binding.tvWidth1; val width2TV = binding.tvWidth2
        val width1ED = binding.editTextWidth1; val width2ED = binding.editTextWidth2
        val spinnerOne = binding.spinner; val spinnerTwo = binding.spinnerForm

        val textD = resources.getString(R.string.diameter)
        val textL = resources.getString(R.string.length)

        showTableTV.setOnClickListener {
            context?.let { it1 -> calculating.showTable(context = it1) }
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

        val stringId = arguments?.getString("id") ?: return
        ingredientsUseCase?.execute(stringId = stringId, data = this)

        button.setOnClickListener {

            val ratio: Double

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

           val roundRatio = String.format("%.1f", ratio).toDouble()

            for (i in 0 until ingredientsList.size) {
                val l = ingredientsList[i]
                val list = Ingredients(ingredient = l.ingredient, count = ((l.count?.toInt())?.times(roundRatio)).toString())
                ingredientsList[i] = list
                adapter.notifyDataSetChanged()
            }
            button.isEnabled = false
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