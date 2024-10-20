package com.artinc.sunset.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.artinc.sunset.R
import com.artinc.sunset.presenter.WeatherPresenter

@Composable
fun WeatherScreen(viewModel: WeatherPresenter) {
    var city by remember { mutableStateOf("") }
    val weatherState = viewModel.weatherState.collectAsState()

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text(stringResource(R.string.search_edittext)) },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { viewModel.getWeather(city) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.search_btn))
        }

        weatherState.value?.let { weather ->
            Text(text = weather.cityName, fontSize = 24.sp)
            Text(text = "${weather.temperature}°C", fontSize = 32.sp)
            Text(text = weather.description.capitalize(), fontSize = 20.sp)
            Image(
                painter = rememberImagePainter(data = weather.iconUrl),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}
