package com.john.gotouchgrass.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.john.gotouchgrass.worker.GrassWorker
import java.lang.System.currentTimeMillis
import java.lang.System.nanoTime
import java.util.*
import java.util.concurrent.TimeUnit

class GrassViewModel(application: Application): ViewModel() {
    private var timeStart = 0
    private var timeTotal = 0
    private var timeSeconds = 0
    private var bitmap: Bitmap? = null

    private val workManager = WorkManager.getInstance(application)


    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
    ) {
        // TODO: create a Data instance with the plantName passed to it
        val oneTimeRequest = OneTimeWorkRequestBuilder<GrassWorker>().setInitialDelay(duration, unit).build()

        // TODO: Generate a OneTimeWorkRequest with the passed in duration, time unit, and data
        //  instance
        workManager.enqueueUniqueWork("Touch Grass", ExistingWorkPolicy.REPLACE, oneTimeRequest)
    }

    // Starts timer
    fun startTime() {
        timeStart = currentTimeMillis().toInt();
    }

    // Stops timer and stores the time length in seconds (initially in milliseconds)
    fun stopTime() {
        timeTotal = currentTimeMillis().toInt()  - timeStart
        timeSeconds = (timeTotal / 1000 % 60)
    }

    // Returns the double time, in minutes
    fun getTime(): Double {
        return timeSeconds / 60.0
    }

    // Returns a string representing the time.
    // If time < 60 seconds, string will say seconds
    // If time > 60 seconds, string will say minutes + seconds
    // If time > 60 minutes, string will say hours + minutes
    fun getTimeString(): String {
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

    fun storeImage(image: Bitmap) {
        bitmap = image;
    }

    fun getImage(): Bitmap? {
        return bitmap
    }
}

class GrassViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GrassViewModel::class.java)) {
            GrassViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}