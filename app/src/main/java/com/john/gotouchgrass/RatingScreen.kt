package com.john.gotouchgrass

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class RatingScreen: AppCompatActivity() {
    private var submitButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rating_screen)
    }

}