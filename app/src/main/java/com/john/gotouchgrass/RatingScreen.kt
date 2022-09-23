package com.john.gotouchgrass

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.john.gotouchgrass.data.DataSource
import com.john.gotouchgrass.databinding.ActivityMainBinding
import com.john.gotouchgrass.databinding.RatingScreenBinding
import com.john.gotouchgrass.model.GrassMoment


class RatingScreen: AppCompatActivity() {
    // Submit button
    private var submitButton: Button? = null
    // Record radio button ratings
    // Record activity details
    // Save time

    companion object {
        var timeSpent: Double = 0.0
    }

    private lateinit var binding: RatingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RatingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.rating_screen)

        // Once submit is pressed, go back to home screen
        submitButton = findViewById(R.id.submit)


        submitButton!!.setOnClickListener {
            // Ends current activity. Currently returns back to home
            // (which is good)
//            binding.tipOptions.checkedRadioButtonId
            var radioGroup = binding.ratingOptions.checkedRadioButtonId

            val ratingString = when (radioGroup) {
                R.id.great_option -> "Great"
                R.id.okay_option -> "Okay"
                else -> "Bad"
            }

            val moment: GrassMoment = GrassMoment(timeSpent, binding.activityText.text.toString(), ratingString)
            DataSource.grassMoments.add(moment)
            val intent = Intent(this, ConfettiScreen::class.java)
            this.startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}