package com.example.mvvm_template_app

import android.app.Application
import com.example.mvvm_template_app.room.MyDatabase

class MyApplication : Application() {
    private val appRunning = false

    override fun onCreate() {
        super.onCreate()

        MyDatabase.getDatabase(this) //--AppDatabase_Impl does not exist
    }
}