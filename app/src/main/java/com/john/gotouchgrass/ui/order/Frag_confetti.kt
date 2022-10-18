package com.john.gotouchgrass.ui.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.gotouchgrass.R

class Frag_confetti : Fragment() {

    companion object {
        fun newInstance() = Frag_confetti()
    }

    private lateinit var viewModel: FragConfettiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frag_confetti, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragConfettiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}