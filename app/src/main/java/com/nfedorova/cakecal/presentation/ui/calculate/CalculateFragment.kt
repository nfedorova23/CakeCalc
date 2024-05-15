package com.nfedorova.cakecal.presentation.ui.calculate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.model.Ingredients
import com.nfedorova.cakecal.databinding.FragmentCalculateBinding
import com.nfedorova.cakecal.domain.repository.invisible
import com.nfedorova.cakecal.domain.repository.recalculating
import com.nfedorova.cakecal.domain.repository.rectangularToRectangular
import com.nfedorova.cakecal.domain.repository.rectangularToRound
import com.nfedorova.cakecal.domain.repository.rectangularToSquare
import com.nfedorova.cakecal.domain.repository.roundToRectangular
import com.nfedorova.cakecal.domain.repository.roundToRound
import com.nfedorova.cakecal.domain.repository.roundToSquare
import com.nfedorova.cakecal.domain.repository.showTable
import com.nfedorova.cakecal.domain.repository.squareToRectangular
import com.nfedorova.cakecal.domain.repository.squareToRound
import com.nfedorova.cakecal.domain.repository.squareToSquare
import com.nfedorova.cakecal.domain.repository.validate
import com.nfedorova.cakecal.domain.repository.visible
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter



class CalculateFragment : Fragment() {

    private lateinit var binding: FragmentCalculateBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private var ingredientsList = arrayListOf<Ingredients>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCalculateBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvCalculate
        makeAdapter()
        getIngredients()
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
            context?.let { it1 -> showTable(it1) }
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
                            invisible(width1TV, width1ED)
                            dH1TV.text = textD
                        }
                        "Rectangular" -> {
                            visible(width1TV, width1ED)
                            dH1TV.text = textL
                        }
                        "Square" -> {
                            invisible(width1TV, width1ED)
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
                            invisible(width2TV, width2ED)
                            dH2TV.text = textD
                        }
                        "Rectangular" -> {
                            visible(width2TV, width2ED)
                            dH2TV.text = textL
                        }
                        "Square" -> {
                            invisible(width2TV, width2ED)
                            dH2TV.text = textL
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        button.setOnClickListener {

            val ratio: Double

            val dlOneED = validate(dH1ED.text.toString())
            val dlTwoED = validate(dH2ED.text.toString())
            val widthOne = validate(width1ED.text.toString())
            val widthTwo = validate(width2ED.text.toString())

            ratio =  recalculating(
                spinnerOne.selectedItem.toString(),
                spinnerTwo.selectedItem.toString(),
                dlOneED, dlTwoED, widthOne, widthTwo
            )

           val roundRatio = String.format("%.1f", ratio).toDouble()

            for (i in 0 until ingredientsList.size) {
                val l = ingredientsList[i]
                val list = Ingredients(l.ingredient, ((l.count?.toInt())?.times(roundRatio)).toString())
                ingredientsList[i] = list
                adapter.notifyDataSetChanged()
            }

            button.isEnabled = false
        }
    }

    private fun getIngredients(){
        val stringId = arguments?.getString("id") ?: return
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
        recipesRef.document(stringId)
            .get()
            .addOnSuccessListener { document ->
                val id = document.id
                ingredientsList = ArrayList()
                val recipeRef = recipesRef.document(id)
                val ingrRef = recipeRef.collection("ingredients")
                ingrRef.get()
                    .addOnSuccessListener { ingredients ->
                        for (ingr in ingredients) {
                            val i = ingr.getString("ingredient")
                            val c = ingr.getString("count")
                            val list = Ingredients(i, c)
                            ingredientsList.add(list)
                        }
                        adapter=ArticleAdapter(ingredientsList)
                        recyclerView.adapter = adapter
                    }
            }
    }
    private fun makeAdapter() {
        adapter = ArticleAdapter(ingredientsList)
        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
    companion object {
        fun newInstance(bundle: Bundle) = CalculateFragment().apply {
            arguments = bundle
        }
    }
}