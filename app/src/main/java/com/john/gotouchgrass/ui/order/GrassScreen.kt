package com.john.gotouchgrass.ui.order

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.telecom.TelecomManager.EXTRA_LOCATION
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.network.WeatherApi
import com.john.gotouchgrass.viewmodel.GrassViewModel
import com.john.gotouchgrass.viewmodel.ReminderDialogFragment
import java.util.*
import java.util.concurrent.TimeUnit


class GrassScreen : Fragment()  {
    private var _binding: FragmentGrassScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GrassViewModel by activityViewModels()
    private val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null
    private lateinit var weatherViewModel: GrassScreenViewModel
    private lateinit var fusedLocationProviderContent: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        _binding = FragmentGrassScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val weatherAPI = WeatherApi
        val viewModelFactory = SearchViewModelFactory(weatherAPI)
        weatherViewModel = ViewModelProvider(this, viewModelFactory).get(GrassScreenViewModel::class.java)

        binding.grassImage.setOnClickListener {
            viewModel.startTime()
            findNavController().navigate(R.id.action_grassScreen_to_homeScreen)
        }

        fusedLocationProviderContent = activity?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!

        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(1)
            fastestInterval = TimeUnit.SECONDS.toMillis(10)
            maxWaitTime = TimeUnit.MINUTES.toMillis(1)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                currentLocation = locationResult.lastLocation
                if (currentLocation != null) {
                    viewModel.setLocation(currentLocation!!.latitude, currentLocation!!.longitude)
                }
            }
        }

        getCurrentLocation()

        binding.reminderButton.setOnClickListener {
            val dialog = ReminderDialogFragment()
            dialog.show(childFragmentManager, "DialogFragment")
        }

        if (viewModel.getTemp() != null) {
            binding.tempTxt.text = viewModel.getTemp().toString();
        }


        super.onCreate(savedInstanceState)
        root.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeRight() {
                super.onSwipeRight()
                Log.d( "Right", "WENT right")
                findNavController().navigate(R.id.action_grassScreen_to_frag_log)
            }
//            override fun onSwipeLeft() {
//                super.onSwipeLeft()
//                Log.d( "Left", "WENT left")
//                findNavController().navigate(R.id.action_grassScreen_to_frag_log)
//                Toast.makeText(activity, "Swipe Left gesture detected",
//                    Toast.LENGTH_SHORT)
//                    .show()
//            }
//            override fun onSwipeUp() {
//                super.onSwipeUp()
//                Toast.makeText(activity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            override fun onSwipeDown() {
//                super.onSwipeDown()
//                Toast.makeText(activity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
//                    .show()
//            }
        })
        return root
    }

    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (activity?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED && activity?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    Log.d("hello", "entering4")
                    return
                }
                activity?.let {
                    fusedLocationProviderContent.lastLocation.addOnCompleteListener(it) { task ->
                        val location: Location? = task.result
                        if (location != null) {
                            Log.d("hello", "entering5")
                            viewModel.setLocation(location.latitude, location.longitude)
                            weatherViewModel.getTemp(location.latitude, location.longitude, viewModel, binding)
                            Log.d("hello", location.latitude.toString())
                            Log.d("hello", location.longitude.toString())
                        } else {
                            Log.d("hello", "entering6")
                            fusedLocationProviderContent.requestLocationUpdates(
                                locationRequest, locationCallback, Looper.getMainLooper())
                            Toast.makeText(activity, "Location Unavailable - Unable to Display Weather", Toast.LENGTH_SHORT).show()
                        }
                        }
                    }
                } else {
                Log.d("hello", "entering7")
                Toast.makeText(activity, "Turn on Location for Weather Services", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.d("hello", "entering8")
            requestPermission()
        }
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_ACCESS_LOCATION)
        }
        Log.d("hello", "entering9")
    }

    private fun checkPermissions(): Boolean {
        if (activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
            == PackageManager.PERMISSION_GRANTED &&
            activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } ==
                PackageManager.PERMISSION_GRANTED) {
            Log.d("hello", "entering10")
            return true
        }
        Log.d("hello", "entering11")
        return false
    }

    private fun isLocationEnabled(): Boolean {
        Log.d("hello", "entering12")
        val locationManager: LocationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            Log.d("hello", "entering1")
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
                Log.d("hello", "entering3")
            } else {
                Log.d("hello", "entering2")
                Toast.makeText(activity, "Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}