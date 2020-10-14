package com.example.mvvm_template_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.room.converter.UserConverter

@Database(
    entities = [User::class ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    UserConverter::class
)
abstract class MyDatabase: RoomDatabase() {

    abstract fun userDao(): UsersDao

    companion object{
        const val DATABASE_NAME: String = "mvvm_db"
    }
}