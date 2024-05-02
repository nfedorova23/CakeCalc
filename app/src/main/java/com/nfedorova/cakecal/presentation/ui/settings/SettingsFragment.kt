package com.nfedorova.cakecal.presentation.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nfedorova.cakecal.databinding.FragmentSettingsBinding
import com.nfedorova.cakecal.presentation.ui.settings.register.LogInActivity
import com.nfedorova.cakecal.presentation.ui.settings.register.SignInActivity


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exit = binding.exitButton
        exit.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("KEY", Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                if (sharedPreferences.getString("KEY", "-9") != "-9"){
                    sharedPreferences.edit().remove("KEY").apply()
                    val intent = Intent(activity, LogInActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }




    companion object {
        const val TAG_SETTINGS = "TAG_SETTINGS"
        fun newInstance() = SettingsFragment()
    }
}