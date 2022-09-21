package com.john.gotouchgrass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop

class MainActivity : AppCompatActivity() {
    // Declaration for "buttons"
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

        // When user clicks the image, update the state information and proceed
        actionImage!!.setOnClickListener {
            clickActionImage()
        }

        // Setting up log button
        logButton = findViewById(R.id.button2)
        logButton!!.setOnClickListener {
            val intent = Intent(this, RatingScreen::class.java)
            this.startActivity(intent)
        }
    }

    // State saver
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ACTION_STATE, currentState)
        super.onSaveInstanceState(outState)
    }

    // Updates state on click. Then update the view elemetns
    private fun clickActionImage() {
        when (currentState) {
            GRASS -> {
                // Grass should bring us to the home button
                currentState = HOME
                // TODO: START TIME
            };
            HOME -> {
                // Grass should bring us to the home button
                currentState = GRASS
                // TODO: END TIME
            };
        }

        setViewElements()
    }

    // Updates view elements based on current state
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.Welcome_txt)

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