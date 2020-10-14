package com.example.mvvm_template_app.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_template_app.models.User

interface MainRepository {

    fun getUsers(): MutableLiveData<MutableList<User>>
    fun getById(id: Int): LiveData<User>
}