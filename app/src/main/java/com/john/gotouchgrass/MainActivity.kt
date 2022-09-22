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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.lang.System
import java.lang.System.currentTimeMillis
import android.widget.Toast

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
    // Time variables
    private var timeStart = 0;
    private var timeEnd = 0;


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
            val intent = Intent(this, LogScreen::class.java)
            this.startActivity(intent)
        }
    }

    // State saver
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ACTION_STATE, currentState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestart() {
        super.onRestart()
    }

    // Updates state on click. Then update the view elemetns
    private fun clickActionImage() {
        when (currentState) {
            GRASS -> {
                currentState = HOME
                // Log current start time
                timeStart = currentTimeMillis().toInt();
                // Text pop-up to indicate to users that their start time has been recorded
                val toast = Toast.makeText(this, "Start Time Logged", Toast.LENGTH_SHORT)
                toast.show()
            };
            HOME -> {
                // Log current end time
                timeEnd = currentTimeMillis().toInt();
                // Switch to rating screen
                // TODO: CAMERON, SEND (time end - time start) / 1000 to the rating screen
                // TODO: /1000 because time is measured in milliseconds
                val intent = Intent(this, RatingScreen::class.java)
                this.startActivity(intent)
                // TODO: COROUTINE TO ENSURE STATE CHANGE HAPPENS AFTER ACTIVITY
                currentState = GRASS
                // startActivityForResult may be our solution, it is async and we wait for a result
                // before doing something
                // can have onResult returns 0 -> stay @ home screen
                // onResult returns 1 -> change to grass string
                // we could then configure this so that the back button returns a 0
                // and clicking another button returns 1 depending on the context
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
//                textAction.text = ((timeEnd-timeStart)/1000).toString() + " seconds";
                actionImage?.setImageResource(R.drawable.starting_house)

            }
        }
    }
}