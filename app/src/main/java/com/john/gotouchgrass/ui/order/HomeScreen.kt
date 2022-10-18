package com.john.gotouchgrass.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.grassImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_frag_rating)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

