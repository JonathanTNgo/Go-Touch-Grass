package com.john.gotouchgrass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.lang.System.currentTimeMillis
import android.widget.Toast

/**
 * This is the MainActivity Class. It handles the main functionality
 * of the app switching from Grass to Home Views.
 */
class MainActivity : AppCompatActivity() {
    // Declarations for "buttons"
    private var actionImage: ImageView? = null
    private var logButton: Button? = null
    // States and variables
    private var ACTION_STATE = "ACTION_STATE"
    private val HOME = "home"
    private val GRASS = "grass"
    // DEFAULT state should be set to grass
    private var currentState = GRASS
    // Switching intents
    private lateinit var listIntent: Intent
    // Time variables
    private var timeStart = 0;
    private var timeEnd = 0;
    private var clickedHouse = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting default values
        if (savedInstanceState != null) {
            currentState = savedInstanceState.getString(ACTION_STATE, GRASS)
        }

        // Attach image to id
        actionImage = findViewById(R.id.main_image)
        // Set elements according to current state
        setViewElements()

        // When user clicks the image, update the state information and proceeds
        // based on that.
        actionImage!!.setOnClickListener {
            clickActionImage()
        }

        // Setting up log button. The LogButton will take you to the LogScreen,
        // which will display all past grass moments.
        logButton = findViewById(R.id.past_grass_button)
        logButton!!.setOnClickListener {
            val intent = Intent(this, LogScreen::class.java)
            this.startActivity(intent)
        }
    }

    // State saver
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ACTION_STATE, currentState)
        super.onSaveInstanceState(outState)
    }

    // Updates state on click. Then update the view elements.
    private fun clickActionImage() {
        when (currentState) {
            // When the image is clicked in the GRASS state, it is moved to the HOME state.
            GRASS -> {
                currentState = HOME
                // Log current start time
                timeStart = currentTimeMillis().toInt();
                // Text pop-up to indicate to users that their start time has been recorded
                val toast = Toast.makeText(this, "Start Time Logged", Toast.LENGTH_SHORT)
                toast.show()
            };

            // When the image is clicked in the HOME state, the timer ends and the screen
            // is switched.
            HOME -> {
                // Log current end time
                timeEnd = currentTimeMillis().toInt();
                // Switch to rating screen
                val intent = Intent(this, RatingScreen::class.java)
                RatingScreen.timeSpent = (timeEnd - timeStart) / (1000.0 * 60)
                Log.d("TIME", RatingScreen.timeSpent.toString())
                this.startActivity(intent)
            };
        }

        // The view elements are set based on the current state.
        setViewElements()
    }

    // Updates view elements based on current state
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.Welcome_txt)

        // The text and image change depending on the current state.
        when (currentState) {
            GRASS -> {
                textAction.text = resources.getString(R.string.grass_txt)
                actionImage?.setImageResource(R.drawable.starting_grass)
            }

            HOME -> {
                textAction.text = resources.getString(R.string.home_txt)
                actionImage?.setImageResource(R.drawable.starting_house)
            }
        }
    }
}