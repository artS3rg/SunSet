package com.artinc.sunset.model

import com.artinc.sunset.network.WeatherApiService

class WeatherRepositoryImpl(private val apiService: WeatherApiService) : WeatherRepository {
    override suspend fun fetchWeather(city: String): WeatherResponse {
        return apiService.getWeather(city, "da2dff0400b7607983581a3464ed3d1e")
    }
}