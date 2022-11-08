package com.kubrayildirim.nasaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kubrayildirim.nasaapp.R
import com.kubrayildirim.nasaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setNavigationBar()
    }

    private fun setNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.curiosity -> {
                    navController.navigate(R.id.curiosityFragment)
                    true
                }
                R.id.opportunity -> {
                    navController.navigate(R.id.opportunityFragment)
                    true
                }
                else -> {
                    navController.navigate(R.id.opportunityFragment)
                    true
                }
            }
        }
    }
}