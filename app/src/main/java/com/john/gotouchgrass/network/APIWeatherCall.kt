package com.john.gotouchgrass.network

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.john.gotouchgrass.BuildConfig
import com.john.gotouchgrass.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//class APIWeatherCall {
//
//    private val weatherService = createRetrofit()
//
//    private val _weather = MutableLiveData<Weather?>()
//    val currentWeather: LiveData<Weather?> = _weather
//
//    fun loadCurrentTemp(zip: String) {
//        val call = weatherService.getTemp(zip, BuildConfig.OPEN_WEATHER_API_KEY, "imperial")
//        call.enqueue(object : Callback<Weather> {
//            override fun onFailure(call: Call<Weather>, t: Throwable) {
//                Log.e(APIWeatherCall::class.java.simpleName, "error loading current weather", t)
//            }
//
//            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                val weatherResponse = response.body()
//                Log.v("response:", weatherResponse.toString())
//                if (weatherResponse != null) {
//                    _weather.value = weatherResponse
//                }
//            }
//        })
//    }
//}