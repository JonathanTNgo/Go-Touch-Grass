package com.john.gotouchgrass.ui.order

import android.util.Log
import androidx.lifecycle.*
import com.john.gotouchgrass.databinding.FragmentGrassScreenBinding
import com.john.gotouchgrass.network.WeatherApi
import com.john.gotouchgrass.viewmodel.GrassViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * The [ViewModel] that is attached to the [GrassScreenFragment].
 */
class GrassScreenViewModel(private val weatherAPI: WeatherApi) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val key = "0a7563fcf28840e489d014cfa60b961b"


    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    private var _temp = MutableLiveData<String?>()
    val temp: LiveData<String?> get() = _temp

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    fun getTemp(city: String, viewModel: GrassViewModel, binding: FragmentGrassScreenBinding) {
        viewModelScope.launch {
            try {
                //val response = weatherAPI.retrofitService.getTemp(city, "US", BuildConfig.OPEN_WEATHER_API_KEY)
                val response = weatherAPI.retrofitService.getTemp(city, "US", key)
                _status.value = "Success"
                // Convert Celsius to Fahrenheit
                var celsius: String? = null
                Log.d("RESPONSE", response.toString())
                if (response.data.isNotEmpty()) {
                    celsius = (response.data[0].temp * (9.0 / 5) + 32).roundToInt().toString() + "°F"
                }
                _temp.value = celsius
                if (celsius != null) {
                    Log.d("TEMP", celsius)
                }
                viewModel.setTemp(celsius)
                binding.tempTxt?.text = celsius
                Log.d("WEATHER", "success")
            } catch (e: Exception) {
                Log.e("STACK TRACE:", e.stackTraceToString())
                _status.value = "Failure"
                _temp.value = null
                Log.d("WEATHER", "failure")
            }
        }
    }
}


class SearchViewModelFactory(private val weatherAPI: WeatherApi): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GrassScreenViewModel(weatherAPI) as T
    }
}