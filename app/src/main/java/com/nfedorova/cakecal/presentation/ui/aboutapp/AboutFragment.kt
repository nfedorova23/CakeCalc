package com.nfedorova.cakecal.presentation.ui.aboutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nfedorova.cakecal.data.datasource.database.UserDataSourceImpl
import com.nfedorova.cakecal.data.repository.UserRepositoryImpl
import com.nfedorova.cakecal.databinding.FragmentAboutBinding
import com.nfedorova.cakecal.domain.usecase.LogoutUseCase


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val userRepository by lazy {
        context?.let { UserDataSourceImpl(context = it) }?.let { UserRepositoryImpl(dataSource = it) } }
    private val logoutUseCase by lazy { userRepository?.let { LogoutUseCase(userRepository = it) } }


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
            logoutUseCase?.execute()
        }
    }
}