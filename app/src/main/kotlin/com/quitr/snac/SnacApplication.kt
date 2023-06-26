package com.quitr.snac

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SnacApplication: Application() {
    init {
        Log.d("Happlication", "Chilling in the app: ")
    }
}