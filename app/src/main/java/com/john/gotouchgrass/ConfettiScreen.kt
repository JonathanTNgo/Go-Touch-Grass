package com.john.gotouchgrass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ConfettiScreen : AppCompatActivity() {
    private var homeButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confetti_screen)
        homeButton = findViewById(R.id.button)
        // TODO: ON BUTTON CLICK, FINISH ACTIVITY AND RETURN HOME
        // (KILL EVERY ACTIVITY, START A FRESH NEW ACTIVITY)
        homeButton!!.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}
