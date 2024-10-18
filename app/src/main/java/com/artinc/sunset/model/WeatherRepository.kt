package com.artinc.sunset.model

interface WeatherRepository {
    suspend fun fetchWeather(city: String): WeatherResponse
}