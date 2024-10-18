package com.artinc.sunset

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.artinc.sunset.presenter.WeatherPresenter
import com.artinc.sunset.ui.WeatherScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    // Получаем WeatherPresenter через Koin
    private val weatherPresenter: WeatherPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            // Устанавливаем WeatherScreen в качестве UI
            WeatherScreen(weatherPresenter)
        }
    }
}