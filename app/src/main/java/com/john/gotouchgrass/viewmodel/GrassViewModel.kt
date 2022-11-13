package com.john.gotouchgrass.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
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

class GrassViewModel(): ViewModel() {
    private var timeStart = 0
    private var timeTotal = 0
    private var timeSeconds = 0
    private var bitmap: Bitmap? = null
    private var temp: String? = null
    private var city: String? = null

    private var workManager: WorkManager? = null;


    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        application: Application
    ) {

        if (workManager == null) {
            workManager = WorkManager.getInstance(application)
        }
        val oneTimeRequest = OneTimeWorkRequestBuilder<GrassWorker>().setInitialDelay(duration, unit).build()

        workManager?.enqueueUniqueWork("Touch Grass", ExistingWorkPolicy.REPLACE, oneTimeRequest)
    }

    fun setCity(curCity: String?) {
        city = curCity
    }

    fun getCity(): String? {
        return city
    }

    fun setTemp(curTemp: String?) {
        if (curTemp == null) {
            temp = null
            Log.d("temp", "is null")
        } else {
            temp = curTemp.toString()
        }
    }

    fun getTemp(): String? {
        return temp
    }

    // Starts timer
    fun startTime() {
        timeStart = SystemClock.uptimeMillis().toInt();
        Log.d("Time 1", timeStart.toString())
    }

    // Stops timer and stores the time length in seconds (initially in milliseconds)
    fun stopTime() {
        timeTotal = SystemClock.uptimeMillis().toInt() - timeStart
        Log.d("Time 2", timeTotal.toString())
        timeSeconds = (timeTotal / 1000 % 60)
        Log.d("Time 3", timeSeconds.toString())
    }

    // Returns the double time, in minutes
    fun getTime(): Double {
        Log.d("Time 4", timeSeconds.toString())
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

//class GrassViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(GrassViewModel::class.java)) {
//            GrassViewModel(application) as T
//        } else {
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
//}