package com.example.mvvm_template_app.repositories

import androidx.lifecycle.LiveData
import com.example.mvvm_template_app.api.RetrofitBuilder
import com.example.mvvm_template_app.models.UserDto
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserRepository {
    var job: CompletableJob? = null

    fun getUser(userId: String) : LiveData<UserDto> {
        job = Job()
        return object : LiveData<UserDto>() {
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) { // Sets value of LiveData on Main thread
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