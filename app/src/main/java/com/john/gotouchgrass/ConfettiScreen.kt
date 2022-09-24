package com.john.gotouchgrass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ConfettiScreen : AppCompatActivity() {
    private var homeButton: Button? = null

    // Test commit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confetti_screen)
        homeButton = findViewById(R.id.home_button)

        // The text element is set to display the amount of time a user spent outside.
        var textElement: TextView = findViewById(R.id.time_text)
        textElement.text = this.resources?.getString(R.string.minute_string, RatingScreen.timeSpent)

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
