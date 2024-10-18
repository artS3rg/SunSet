package com.artinc.sunset.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.sunset.model.WeatherData
import com.artinc.sunset.model.WeatherRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherPresenter(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherData?>(null)
    val weatherState: StateFlow<WeatherData?> = _weatherState

    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                Log.d("WeatherPresenter", "Start")
                val weatherResponse = repository.fetchWeather(city)
                val weatherData = WeatherData(
                    cityName = weatherResponse.name,
                    temperature = weatherResponse.main.temp,
                    description = weatherResponse.weather[0].description,
                    iconUrl = "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}@2x.png"
                )
                _weatherState.value = weatherData
                Log.d("WeatherPresenter", "Weather data: $weatherData")
            } catch (e: Exception) {
                Log.d("WeatherPresenter", "Error: ${e.message}")
            }
        }
    }
}