package com.dilsahozkan.papafood

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PapaFoodApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.v("PaparaApp", "onCreate")
    }
}