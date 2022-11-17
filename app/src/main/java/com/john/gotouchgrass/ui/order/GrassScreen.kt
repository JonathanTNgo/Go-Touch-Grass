package com.john.gotouchgrass.ui.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.network.WeatherApi
import com.john.gotouchgrass.viewmodel.GrassViewModel
import com.john.gotouchgrass.viewmodel.ReminderDialogFragment


class GrassScreen : Fragment()  {
    private var _binding: FragmentGrassScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var listIntent: Intent
    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        _binding = FragmentGrassScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val weatherAPI = WeatherApi
        val viewModelFactory = SearchViewModelFactory(weatherAPI)
        val weatherViewModel = ViewModelProvider(this, viewModelFactory).get(GrassScreenViewModel::class.java)

        binding.grassImage.setOnClickListener {
            viewModel.startTime()
            findNavController().navigate(R.id.action_grassScreen_to_homeScreen)
        }

        binding.setCityButton.setOnClickListener {
            val city: String = binding.citySearch.editText?.text.toString()

            Toast.makeText(activity, "Getting weather data...", Toast.LENGTH_LONG).show()

            // If a city has been entered, get the temp in that city.
            if (city != null && city.isNotEmpty()) {
                Log.d("city", city)

                weatherViewModel.getTemp(binding.citySearch.editText?.text.toString(), viewModel, binding)

            }
        }

        binding.reminderButton.setOnClickListener {
            val dialog = ReminderDialogFragment()
            dialog.show(childFragmentManager, "DialogFragment")
        }

//        binding.pastGrassButton .setOnClickListener {
//            findNavController().navigate(R.id.action_grassScreen_to_logScreen)
//        }

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
}