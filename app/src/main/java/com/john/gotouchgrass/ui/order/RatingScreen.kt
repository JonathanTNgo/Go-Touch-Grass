package com.john.gotouchgrass.ui.order

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.john.gotouchgrass.R
import com.john.gotouchgrass.data.DataSource
import com.john.gotouchgrass.databinding.RatingScreenBinding
import com.john.gotouchgrass.model.GrassMoment


class RatingScreen: AppCompatActivity() {
    // Submit button
    private var submitButton: Button? = null

    // This companion object helps us track the time a user spent outside.
    companion object {
        var timeSpent: Double = 0.0
    }

    private lateinit var binding: RatingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RatingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // The submit button is grabbed.
        submitButton = findViewById(R.id.submit)

        // If the submit button is clicked, the screen is switched to the Confetti Screen.
        submitButton!!.setOnClickListener {
            var radioGroup = binding.ratingOptions.checkedRadioButtonId

            // The user's rating of their time outside is updated.
            val ratingString = when (radioGroup) {
                R.id.great_option -> "Great"
                R.id.okay_option -> "Okay"
                else -> "Bad"
            }

            // A new GrassMoment is created and added to the RecyclerView.
            //val moment: GrassMoment = GrassMoment(timeSpent, binding.activityText.text.toString(), ratingString)
            //DataSource.grassMoments.add(moment)

            // The Confetti Screen is opened.
            val intent = Intent(this, ConfettiScreen::class.java)
            this.startActivity(intent)
        }

        // There is no back button for this screen.
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}