package com.john.gotouchgrass.model

import androidx.lifecycle.ViewModel
import java.lang.System.currentTimeMillis

class GrassViewModel: ViewModel() {
    private var timeStart = 0
    private var timeTotal = 0

    // text
    // private var text

    fun startTime() {
        timeStart = currentTimeMillis().toInt();
    }

    fun stopTime() {
        timeTotal = currentTimeMillis().toInt() - timeStart
    }

    fun getTime(): Int {
        return timeTotal
    }

    fun resetTime() {
        timeStart = 0
        timeTotal = 0
    }
}