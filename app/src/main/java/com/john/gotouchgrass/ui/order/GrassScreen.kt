package com.john.gotouchgrass.ui.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.model.GrassViewModel
import java.lang.Math.abs

class GrassScreen : Fragment()  {
    private var _binding: FragmentGrassScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var listIntent: Intent

    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGrassScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.grassImage.setOnClickListener {
            viewModel.startTime()
            findNavController().navigate(R.id.action_grassScreen_to_homeScreen)
        }

        binding.pastGrassButton .setOnClickListener {
            findNavController().navigate(R.id.action_grassScreen_to_logScreen)
        }
        super.onCreate(savedInstanceState)
        root.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Log.d( "Left", "WENT left")
                Toast.makeText(activity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                Log.d( "Right", "WENT right")
                Toast.makeText(
                    activity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onSwipeUp() {
                super.onSwipeUp()
                Toast.makeText(activity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeDown() {
                super.onSwipeDown()
                Toast.makeText(activity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return root
    }
}