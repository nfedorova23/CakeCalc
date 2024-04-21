package com.nfedorova.cakecal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nfedorova.cakecal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var sharedPreferences = getSharedPreferences("KEY", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("KEY", "9").commit()
        navView = binding.bottomNavigationView
        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.recipes_menu, R.id.calculate_menu, R.id.settings_menu))
        val appBarConfigurationOne = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfigurationOne)
        navView.setupWithNavController(navController)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {}
}