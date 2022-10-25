package com.john.gotouchgrass.model

import androidx.lifecycle.ViewModel
import java.lang.System.currentTimeMillis

class GrassViewModel: ViewModel() {
    private var timeStart = 0
    private var timeTotal = 0
    private var timeSeconds = 0

    // Starts timer
    fun startTime() {
        timeStart = currentTimeMillis().toInt();
    }

    // Stops timer and stores the time length in seconds (initially in milliseconds)
    fun stopTime() {
        timeTotal = currentTimeMillis().toInt() - timeStart
        timeSeconds = (timeTotal / 1000 % 60)
    }

    // Returns the timeSeconds as a double for printf
    fun getSeconds(): Double {
        return timeSeconds.toDouble()
    }

    // Returns a string representing the time.
    // If time < 60 seconds, string will say seconds
    // If time > 60 seconds, string will say minutes + seconds
    // If time > 60 minutes, string will say hours + minutes
    fun getTime(): String {
        when (timeSeconds)
        {
            // Print in seconds
            in 0..59 -> return "You spent " + timeSeconds + " seconds outside!"
            // Print only for 1 minute
            60 -> return "You spent " + (timeSeconds / 60) + " minute outside!"
            // Print in minutes and seconds
            in 61..3599 -> return "You spent " + (timeSeconds / 60) + " minutes and " + (timeSeconds % 60) + " second(s) outside!"
            // Print only for 1 hour
            3600 -> return "You spent " + (timeSeconds / 60 / 60) + " hour outside!"
            // Print in hours and minutes
            else -> return "You spent " + (timeSeconds / 60 / 60) + " hours and " + (timeSeconds / 60 % 60) + " minute(s) outside!"
        }
    }

    fun resetTime() {
        timeStart = 0
        timeTotal = 0
    }
}