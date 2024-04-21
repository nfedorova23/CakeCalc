package com.nfedorova.cakecal.presentation.ui.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.nfedorova.cakecal.MainActivity
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.databinding.FragmentSettingsBinding
import com.nfedorova.cakecal.presentation.ui.settings.register.LogInActivity


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    companion object {
        const val TAG_SETTINGS = "TAG_SETTINGS"
        fun newInstance() = SettingsFragment()
    }
}