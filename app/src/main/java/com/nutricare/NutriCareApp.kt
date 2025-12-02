package com.nutricare

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NutriCareApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
