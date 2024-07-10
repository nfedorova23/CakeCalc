package com.nfedorova.cakecal.presentation.ui.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nfedorova.cakecal.databinding.FragmentAboutBinding
import com.nfedorova.cakecal.domain.utils.ChangeOfActivityLogOut
import com.nfedorova.cakecal.presentation.state.viewmodel.recipes.AboutRecipesViewModel
import com.nfedorova.cakecal.presentation.ui.register.LogInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutFragment : Fragment(), ChangeOfActivityLogOut {

    private lateinit var binding: FragmentAboutBinding
    private val viewModel by viewModel<AboutRecipesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logOut = binding.exitButton
        logOut.setOnClickListener {
            viewModel.logOut(this)
        }
    }

    override fun changeOfActivity() {
        val sharedPreferences = activity?.getSharedPreferences("KEY", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("KEY", "-9")?.apply()
        startActivity(Intent(context, LogInActivity::class.java))
    }
}