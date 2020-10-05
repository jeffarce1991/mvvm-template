package com.example.mvvm_template_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.repositories.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay


class MainViewModel: ViewModel() {


    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    private var mUsers: MutableLiveData<MutableList<User>>? = null
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    fun init() {
        mUsers = MainRepository.getUsers()
        println("debug : MainViewModel ${mUsers!!.value}")

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