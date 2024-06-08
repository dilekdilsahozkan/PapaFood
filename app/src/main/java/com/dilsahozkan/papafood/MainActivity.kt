package com.dilsahozkan.papafood

import com.dilsahozkan.papafood.common.PreferencesManager
import android.os.Bundle
import com.dilsahozkan.papafood.BuildConfig
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dilsahozkan.papafood.presentation.navigation.MainScreen
import com.dilsahozkan.papafood.ui.theme.PapaFoodTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiKey = BuildConfig.API_KEY
        preferencesManager.storeApiKey(apiKey)

        println("API Key: $apiKey")
        setContent {
            PapaFoodTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()){
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PapaFoodTheme {

    }
}