package com.artinc.sunset.di

import com.artinc.sunset.model.WeatherRepository
import com.artinc.sunset.model.WeatherRepositoryImpl
import com.artinc.sunset.network.WeatherApiService
import com.artinc.sunset.presenter.WeatherPresenter
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(WeatherApiService::class.java)
    }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    viewModel { WeatherPresenter(get()) }
}