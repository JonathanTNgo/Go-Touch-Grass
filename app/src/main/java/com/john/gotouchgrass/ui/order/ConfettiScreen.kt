package com.john.gotouchgrass.ui.order

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.john.gotouchgrass.MainActivity
import com.john.gotouchgrass.R
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

class ConfettiScreen : AppCompatActivity() {
    private lateinit var viewKonfetti: KonfettiView
    private var homeButton: Button? = null

    // Test commit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confetti_screen)
        homeButton = findViewById(R.id.home_button)

        // The text element is set to display the amount of time a user spent outside.
        var textElement: TextView = findViewById(R.id.time_text)
        //textElement.text = this.resources?.getString(R.string.minute_string, viewModel.getTime())

        // Confetti set up
        viewKonfetti = findViewById(R.id.konfettiView)
        viewKonfetti.start(Presets.rain())

        // When the home button is clicked, a new MainActivity is started with the default
        // image of grass.
        homeButton!!.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // There is no back button available for this page.
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
