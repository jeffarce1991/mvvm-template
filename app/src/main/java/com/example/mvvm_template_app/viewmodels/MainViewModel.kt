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
import kotlinx.coroutines.*


class MainViewModel(application: Application): AndroidViewModel(application){


    var job: CompletableJob? = null
    var myDatabase: MyDatabase = MyDatabase.getDatabase(application)

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    private var mUsers: MutableLiveData<MutableList<User>>? = null
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    init {
        mUsers = MainRepository(myDatabase.userDao()).getUsers()
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
        MainRepository(myDatabase.userDao()).cancelJobs()
    }
}