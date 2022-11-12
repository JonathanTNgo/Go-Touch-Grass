package com.john.gotouchgrass


import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.ActivityMainBinding

/**
 * This is the MainActivity Class. It handles the main functionality
 * of the app switching from Grass to Home Views.
 */
class MainActivity : AppCompatActivity() {

    // Binding object corresponds to the activity_main.xml layout
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}