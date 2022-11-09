package com.john.gotouchgrass.ui.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.viewmodel.GrassViewModel
import com.john.gotouchgrass.viewmodel.GrassViewModelFactory
import com.john.gotouchgrass.viewmodel.ReminderDialogFragment

class GrassScreen : Fragment()  {
    private var _binding: FragmentGrassScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var listIntent: Intent

    private val viewModel: GrassViewModel by viewModels {
        GrassViewModelFactory(requireActivity().application)
    }

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
                Toast.makeText(
                    activity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()
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