package com.john.gotouchgrass.network

import com.john.gotouchgrass.model.Weather
import com.john.gotouchgrass.model.WeatherForecast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private var weatherURL: String = "http://api.openweathermap.org/data/2.5/";
private val weatherURL: String = "https://api.weatherbit.io/v2.0/"
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .baseUrl(weatherURL)
//    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(weatherURL)
    .build()

//fun createRetrofit(): WeatherAPIService {
//    val retro = Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .baseUrl(weatherURL)
//        .build()
//    return retro.create(WeatherAPIService::class.java)
//}


interface WeatherAPIService {
    @GET("current")
//    suspend fun getTemp(@Query("city") city: String,
//                @Query("country") country: String,
//                @Query("key") key: String): WeatherForecast
    suspend fun getTemp(@Query("lat") latitude: Double,
                        @Query("lon") longitude: Double,
                        @Query("key") key: String): WeatherForecast
}

object WeatherApi {
    val retrofitService: WeatherAPIService by lazy { retrofit.create(WeatherAPIService::class.java) }
}