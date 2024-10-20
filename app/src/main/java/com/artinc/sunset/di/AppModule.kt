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
import java.util.concurrent.TimeUnit

val appModule = module {

    // Создание кастомного OkHttpClient с тайм-аутами
    single {
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)  // Тайм-аут на подключение
            .readTimeout(20, TimeUnit.SECONDS)     // Тайм-аут на чтение
            .writeTimeout(20, TimeUnit.SECONDS)    // Тайм-аут на запись
            .build()
    }

    // Retrofit с настроенным OkHttpClient
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())  // Передача кастомного OkHttpClient
            .build()
            .create(WeatherApiService::class.java)
    }

    // Репозиторий
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // Презентер
    viewModel { WeatherPresenter(get()) }
}
