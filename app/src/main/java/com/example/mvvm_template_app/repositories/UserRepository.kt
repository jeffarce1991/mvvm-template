package com.example.mvvm_template_app.repositories

import androidx.lifecycle.LiveData
import com.example.mvvm_template_app.api.RetrofitBuilder
import com.example.mvvm_template_app.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserRepository {
    var job: CompletableJob? = null

    fun getUser(userId: String) : LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            job.complete()
                        }
                    }
                }
            }
        }
    }

    fun canJobs() {
        job?.cancel()
    }
}