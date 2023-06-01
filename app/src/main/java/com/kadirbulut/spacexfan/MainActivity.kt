package com.kadirbulut.spacexfan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kadirbulut.spacexfan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_rockets -> {
                    navController.navigate(R.id.navigation_rockets)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_favourites -> {
                    navController.navigate(R.id.navigation_favourites)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_upcoming_launches -> {
                    navController.navigate(R.id.navigation_upcoming_launches)
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener true
            }
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
