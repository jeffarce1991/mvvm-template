package com.example.mvvm_template_app.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.repositories.MainRepositoryImpl
import kotlinx.coroutines.*


class MainViewModel
@ViewModelInject
constructor(
    private val mainRepositoryImpl: MainRepositoryImpl
): ViewModel(){


    var job: CompletableJob? = null

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    private var mUsers: MutableLiveData<MutableList<User>>? = null
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    init {
        mUsers = mainRepositoryImpl.getUsers()
    }

    fun  addNewValue(user: User) {
        mIsUpdating.postValue(true)
        job = Job()
        job?.let {
            CoroutineScope(Dispatchers.IO + it).launch(handler) {
                delay(1000)
                val users: MutableList<User>? = mUsers!!.value
                users!!.add(user)
                mUsers!!.postValue(users)
                mIsUpdating.postValue(false)
                it.complete()
            }
        }

    }

    fun getUsers() : MutableLiveData<MutableList<User>>? {
        return mUsers
    }

    fun getIsUpdating(): LiveData<Boolean?>? {
        return mIsUpdating
    }

    fun cancelJobs() {
        mainRepositoryImpl.cancelJobs()
    }
}