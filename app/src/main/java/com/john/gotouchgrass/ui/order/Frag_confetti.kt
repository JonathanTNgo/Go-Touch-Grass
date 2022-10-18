package com.john.gotouchgrass.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentFragConfettiBinding
import com.john.gotouchgrass.model.GrassViewModel

class Frag_confetti : Fragment() {
    private var _binding: FragmentFragConfettiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragConfettiBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.timeText.text = this.resources?.getString(R.string.minute_string, viewModel.getTime());
        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_frag_confetti_to_grassScreen)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
