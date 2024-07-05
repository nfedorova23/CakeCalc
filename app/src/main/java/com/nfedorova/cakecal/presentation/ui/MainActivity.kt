package com.nfedorova.cakecal.presentation.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nfedorova.cakecal.R
import com.nfedorova.cakecal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("KEY", "9").apply()
        navView = binding.bottomNavigationView
        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipes_menu, R.id.calculate_menu, R.id.about_menu
            )
        )
        val appBarConfigurationOne = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(
            navController = navController,
            configuration = appBarConfigurationOne
        )
        navView.setupWithNavController(navController = navController)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController = navController) || super.onOptionsItemSelected(
            item
        )
    }

    override fun onBackPressed() {}

}