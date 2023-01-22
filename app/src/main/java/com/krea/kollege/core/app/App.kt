package com.krea.kollege.core.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("58ef0a14-610e-4941-99b3-073af853964d");
    }
}