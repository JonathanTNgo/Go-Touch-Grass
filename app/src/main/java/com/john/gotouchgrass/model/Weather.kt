package com.john.gotouchgrass.model

//data class Coordinates(val lat: Float, val long: Float)
//data class Forecast(val temp: Float)
//
//data class Weather(
//    val name: String,
//    val coord: Coordinates,
//    val main: Forecast
//)

import com.squareup.moshi.Json


data class Data(
    val city_name: String,
    val country_code: String,
    val state_code: String,
    val timezone: String,
    val lat: Double,
    val lon: Double,
    val station: String,
    val sources: List<String>,
    val vis: Double,
    val rh: Double,
    val dewpt: Double,
    val wind_dir: Double,
    val wind_cdir: String,
    val wind_cdir_full: String,
    val wind_spd: Double,
    val gust: Double,
    val temp: Double,
    val app_temp: Double,
    val clouds: Double,
    val weather: Weather,
    val datetime: String,
    val ob_time: String,
    val ts: Double,
    val sunrise: String,
    val sunset: String,
    val slp: Double,
    val pres: Double,
    val aqi: Double,
    val uv: Double,
    val solar_rad: Double,
    val ghi: Double,
    val dni: Double,
    val dhi: Double,
    val elev_angle: Double,
    val h_angle: Double,
    val pod: String,
    val precip: Double,
    val snow: Double,
)

data class Weather (
    val icon: String,
    val code: Double,
    val description: String
)

data class WeatherForecast(
    val count: Int,
    val data: List<Data>
)


//data class WeatherDetails(
//    @field:Json(name="icon")
//    val iconName: String?=null,
//    @field:Json(name="main")
//    val weatherType: String?="Unable to get weather."
//)
//
//data class MainWeather(
//    @field:Json(name = "temp")
//    val temp: Float,
//    @field:Json(name="pressure")
//    val pressure: Float,
//    @field:Json(name="humidity")
//    val humidity: Float
//)
//
//data class Weather(
//    @field:Json(name="main")
//    val main: MainWeather?= null,
//    @field:Json(name="name")
//    val name: String ?=null,
//    @field:Json(name="weather")
//    val detailsList: List<WeatherDetails> ?=null
//)
//