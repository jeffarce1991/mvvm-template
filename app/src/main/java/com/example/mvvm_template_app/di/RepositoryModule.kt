package com.example.mvvm_template_app.di

import com.example.mvvm_template_app.repositories.MainRepository
import com.example.mvvm_template_app.room.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(usersDao: UsersDao): MainRepository {
        return MainRepository(usersDao)
    }
}