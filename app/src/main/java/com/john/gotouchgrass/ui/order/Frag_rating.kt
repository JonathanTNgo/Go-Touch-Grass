package com.john.gotouchgrass.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.data.DataSource
import com.john.gotouchgrass.databinding.FragmentFragRatingBinding
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.model.GrassMoment
import com.john.gotouchgrass.model.GrassViewModel

class Frag_rating : Fragment() {
    // Fragment variables
    private var _binding: FragmentFragRatingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragRatingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Disables the hint when the activity text box is clicked
        binding.activityText.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.activityText.setHint("")
            else binding.activityText.setHint("What did you do?\\n Edit me!")
        })

        // When button is clicked, save text and switch to confetti fragment
        binding.submit.setOnClickListener {
            // Sample code for adding image onto recycler view
//            val moment: GrassMoment? = viewModel.getImage()?.let { it1 ->
//                GrassMoment(viewModel.getTime(), binding.activityText.editText?.text.toString(),
//                    it1
//                )
//            }
//
//            if (moment != null) {
//                DataSource.grassMoments.add(moment)
//            }

            val moment: GrassMoment = GrassMoment(viewModel.getTime(), binding.activityText.editText?.text.toString())
            DataSource.grassMoments.add(moment)
            findNavController().navigate(R.id.action_frag_rating_to_frag_confetti)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}