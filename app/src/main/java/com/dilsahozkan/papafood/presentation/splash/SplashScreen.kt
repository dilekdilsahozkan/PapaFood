package com.dilsahozkan.papafood.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dilsahozkan.papafood.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: (Boolean) -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished(true)
    }
    Scaffold(
        containerColor = Color.White,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_sreen),
                contentDescription = "Splash",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}