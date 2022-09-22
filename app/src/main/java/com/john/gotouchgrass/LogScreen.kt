package com.john.gotouchgrass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.john.gotouchgrass.adapter.GrassLogAdapter
import com.john.gotouchgrass.const.Layout
import com.john.gotouchgrass.databinding.ActivityLogScreenBinding

class LogScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLogScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.grassRecyclerView.adapter = GrassLogAdapter(
            applicationContext,
            Layout.VERTICAL
        )

        // Specify fixed size to improve performance
        binding.grassRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}