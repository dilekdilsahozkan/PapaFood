package com.dilsahozkan.papafood

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.dilsahozkan.papafood.common.PreferencesManager
import com.dilsahozkan.papafood.presentation.navigation.BottomBar
import com.dilsahozkan.papafood.presentation.navigation.MainScreen
import com.dilsahozkan.papafood.ui.theme.PapaFoodTheme
import com.dilsahozkan.papafood.workmanager.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(20, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(workRequest)

        val apiKey = BuildConfig.API_KEY
        preferencesManager.storeApiKey(apiKey)

        val startDestination = if (isInternetAvailable(this)) {
            BottomBar.Home.route
        } else {
            BottomBar.Favorite.route
        }

        setContent {
            PapaFoodTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()){
                    MainScreen(navController = navController, startDestination = startDestination)
                }
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PapaFoodTheme {

    }
}