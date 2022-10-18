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
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

// Class for creating the confetti animation
// ** CREDIT TO https://github.com/DanielMartinus/Konfetti/ **
class Presets {
    companion object {
        fun rain(): List<Party> {
            return listOf(
                Party(
                    speed = 0f,
                    maxSpeed = 15f,
                    damping = 0.9f,
                    angle = Angle.BOTTOM,
                    spread = Spread.ROUND,
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(100),
                    position = Position.Relative(0.0, 0.0).between(Position.Relative(1.0, 0.0))
                )
            )
        }
    }
}

class Frag_confetti : Fragment() {
    private lateinit var viewKonfetti: KonfettiView
    private var _binding: FragmentFragConfettiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GrassViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragConfettiBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.timeText.text = this.resources.getString(R.string.minute_string, viewModel.getTime())
        // Confetti set up
        viewKonfetti = binding.konfettiView
        viewKonfetti.start(Presets.rain())
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
