package com.john.gotouchgrass

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class RatingScreen: AppCompatActivity() {
    // Submit button
    private var submitButton: Button? = null
    // Record radio button ratings
    // Record activity details
    // Save time



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rating_screen)

        // Once submit is pressed, go back to home screen
        submitButton = findViewById(R.id.submit)
        submitButton!!.setOnClickListener {
            // Ends current activity. Currently returns back to home
            // (which is good)
            val intent = Intent(this, ConfettiScreen::class.java)
            this.startActivity(intent)
        }
    }

}