package com.example.mvvm_template_app

import android.app.Application
import com.example.mvvm_template_app.room.MyDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}