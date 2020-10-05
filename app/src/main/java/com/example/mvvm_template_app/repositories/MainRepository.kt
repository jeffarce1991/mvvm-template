package com.example.mvvm_template_app.repositories

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_template_app.api.RetrofitBuilder
import com.example.mvvm_template_app.models.User
import kotlinx.coroutines.*


/*
* Singleton pattern
* */
object MainRepository {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    var job: CompletableJob? = null

    fun getUsers(): MutableLiveData<MutableList<User>> {
        println("debug: Getting Users from Remote.")
        job = Job()
        return object : MutableLiveData<MutableList<User>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch(handler) {
                        val users = RetrofitBuilder.apiService.getUsers()
                        withContext(Dispatchers.Main) {
                            value = users
                            println("debug: $users")
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }


    fun cancelJobs() {
        job?.cancel()
    }
}