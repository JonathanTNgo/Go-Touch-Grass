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
    private var actionImage: ImageView? = null
    private var logButton: Button? = null
    private var ACTION_STATE = "ACTION_STATE"
    private var EXPERIENCE = "EXPERIENCE"
    private val HOME = "home"
    private val GRASS = "grass"
    // default state for action = grass
    private var currentState = "grass"


//    private lateinit var binding: ActivityMainBinding
    // build.gradle (app) in android add
    // buildFeatures{
    //  viewBinding = true
    // }
    private lateinit var listIntent: Intent




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting default values
        if (savedInstanceState != null) {
            currentState = savedInstanceState.getString(ACTION_STATE, "grass")
        }

        // Attach image to id
        actionImage = findViewById(R.id.grass)
        // Set elements according to current state
        setViewElements()

        actionImage!!.setOnClickListener {
            // When user clicks the image, update the state information and procede
            clickActionImage()
        }

        logButton = findViewById(R.id.button2)

        logButton!!.setOnClickListener {
            val intent = Intent(this, RatingScreen::class.java)
            Log.d("hello", "click")
            //intent.putExtra("startingTime", System.currentTimeMillis())
            this.startActivity(intent)
            Log.d("hello", "apple")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ACTION_STATE, currentState)
        super.onSaveInstanceState(outState)
    }

    private fun clickActionImage() {
        when (currentState) {
            GRASS -> {
                currentState = HOME
            };
            HOME -> {
                currentState = GRASS
//                val intent = Intent(this, RatingScreen::class.java)
//                //intent.putExtra("startingTime", System.currentTimeMillis())
//                startActivity(intent)
            };
        }

        setViewElements()
    }

    private fun setViewElements() {
        // Temp implementation is for default grass/home
        // TODO: allow user to change "theme" (IE: BEACH)

        val textAction: TextView = findViewById(R.id.Welcome_txt)

        when (currentState) {
            GRASS -> {
                textAction.text = resources.getString(R.string.grass_txt)
                actionImage?.setImageResource(R.drawable.starting_grass)
            }

            HOME -> {
                textAction.text = resources.getString(R.string.home_txt)
                actionImage?.setImageResource(R.drawable.house_cropped)
            }
        }
    }


}