package com.john.gotouchgrass.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.model.GrassViewModel

class GrassScreen : Fragment() {
    private var _binding: FragmentGrassScreenBinding? = null
    private val binding get() = _binding!!

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
        return root
    }
}