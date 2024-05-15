package com.nfedorova.cakecal.presentation.ui.article

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.data.model.Ingredients
import com.nfedorova.cakecal.data.model.Recipes
import com.nfedorova.cakecal.databinding.FragmentArticleBinding
import com.nfedorova.cakecal.presentation.state.adapter.ArticleAdapter


class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var recyclerView: RecyclerView
    private  var adapter: ArticleAdapter = ArticleAdapter(mutableListOf())
    private var ingredientsList = mutableListOf<Ingredients>()
    private val bundle: Bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.rvIngredients
        getArticle()
        val calculate = binding.calculate

        calculate.setOnClickListener {
            it.findNavController().navigate(R.id.action_articleFragment_to_calculateFragment, arguments)
        }

        makeAdapter()

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getArticle(){
        val title = binding.titleTv
        val description = binding.descriptionTv
        val steps = binding.stepsTv
        val stringId = arguments?.getString("id") ?: return
        val recipesRef = FirebaseFirestore.getInstance().collection("recipes")
            recipesRef.document(stringId)
                .get()
                .addOnSuccessListener {document ->
                    val articleList = ArrayList<Recipes>()
                        val id = document.id
                        val titleI = document.getString("title")
                        val descriptionI = document.getString("description")
                        val stepsI = document.getString("steps")
                        val ingrList = ArrayList<Ingredients>()
                        val recipeRef = recipesRef.document(id)
                        val ingrRef = recipeRef.collection("ingredients")
                        ingrRef.get()
                            .addOnSuccessListener {ingredients ->
                                for (ingr in ingredients){
                                    val i = ingr.getString("ingredient")
                                    val c = ingr.getString("count")
                                    val list = Ingredients(i,c)
                                    ingrList.add(list)
                                }
                                val article = Recipes(id, titleI, descriptionI, ingrList, stepsI)
                                articleList.add(article)
                                recyclerView.adapter = ArticleAdapter(ingrList)
                                title.text = titleI
                                description.text = descriptionI
                                steps.text = stepsI
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

    companion object{

        fun newInstance(bundle: Bundle) = ArticleFragment().apply {
            arguments = bundle
        }
    }

}