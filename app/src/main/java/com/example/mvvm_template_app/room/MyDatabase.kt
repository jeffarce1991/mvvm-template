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
        private const val DATABASE_NAME: String = "mvvm_db"

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    DATABASE_NAME
                )

                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }



    }


}