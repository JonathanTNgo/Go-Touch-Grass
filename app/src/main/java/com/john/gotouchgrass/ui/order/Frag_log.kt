package com.john.gotouchgrass.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.adapter.GrassLogAdapter
import com.john.gotouchgrass.const.Layout
import com.john.gotouchgrass.databinding.FragmentFragLogBinding
import com.john.gotouchgrass.viewmodel.GrassViewModel
import com.john.gotouchgrass.R


class Frag_log : Fragment() {
    private var _binding: FragmentFragLogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragLogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        super.onCreate(savedInstanceState)
        binding.grassRecyclerView.adapter = GrassLogAdapter(
            activity?.applicationContext,
            Layout.VERTICAL
        )

        // Specify fixed size to improve performance
        binding.grassRecyclerView.setHasFixedSize(true)

        //Make a button for navigating back to grass screen
        binding.backButton .setOnClickListener {
            findNavController().navigate(R.id.action_frag_log_to_grassScreen)
        }
        root.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Log.d( "Left", "WENT left")
                findNavController().navigate(R.id.action_frag_log_to_grassScreen)
                Toast.makeText(activity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.getItemId()) {
//            R.id.home -> {
//                onBackPressed()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}