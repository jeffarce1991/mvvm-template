package com.example.mvvm_template_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.repositories.MainRepository
import com.example.mvvm_template_app.room.MyDatabase
import com.example.mvvm_template_app.room.UsersDao


class MainViewModel(application: Application): AndroidViewModel(application){


    var myDatabase: MyDatabase = MyDatabase.getDatabase(application)

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    private var mUsers: MutableLiveData<MutableList<User>>? = null
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    fun init() {
        mUsers = MainRepository.getUsers()
        println("debug : MainViewModel ${mUsers!!.value}")

    init {
        mUsers = MainRepository(myDatabase.userDao()).getUsers()
    }

    suspend fun  addNewValue(user: User) {
            delay(1000)
            val currentPlaces: MutableList<User>? = mUsers!!.value
            currentPlaces!!.add(user)
            mUsers!!.postValue(currentPlaces)
            mIsUpdating.postValue(false)

    }

    fun getUsers() : MutableLiveData<MutableList<User>>? {
        return mUsers
    }

    fun getIsUpdating(): LiveData<Boolean?>? {
        return mIsUpdating
    }

    fun cancelJobs() {
        MainRepository.cancelJobs()
    }
}