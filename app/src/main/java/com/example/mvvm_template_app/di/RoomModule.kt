package com.example.mvvm_template_app.di

import android.content.Context
import androidx.room.Room
import com.example.mvvm_template_app.room.MyDatabase
import com.example.mvvm_template_app.room.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): MyDatabase{
        return Room
            .databaseBuilder(
                context,
                MyDatabase::class.java,
                MyDatabase.DATABASE_NAME
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(myDatabase: MyDatabase): UsersDao {
        return myDatabase.userDao()
    }

}