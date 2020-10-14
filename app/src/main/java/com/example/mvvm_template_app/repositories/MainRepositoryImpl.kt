package com.example.mvvm_template_app.repositories

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_template_app.api.UsersApi
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.room.UsersDao
import com.example.mvvm_template_app.utils.NetworkMapper
import kotlinx.coroutines.*
import java.net.UnknownHostException
import javax.inject.Inject


/*
* Singleton pattern
* */
class MainRepositoryImpl
    @Inject
    constructor(
        private val usersDao: UsersDao,
        private val usersApi: UsersApi
    ) : MainRepository {

    private var networkMapper: NetworkMapper = NetworkMapper()

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("debug: Exception thrown: $exception")
    }

    var job: CompletableJob? = null

    override fun getUsers(): MutableLiveData<MutableList<User>> {
        job = Job()
        return object : MutableLiveData<MutableList<User>>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch(handler) {

                        try {
                            val remoteUsers = usersApi.getUsers()

                            for(dto in remoteUsers){
                                usersDao.insert(networkMapper.mapFromDto(dto))
                            }

                            withContext(Dispatchers.Main) {
                                value = networkMapper.mapFromDtoList(remoteUsers) as MutableList<User>
                                println("debug: $value")
                                theJob.complete()
                            }
                        } catch(e: Exception) {
                            if (e is UnknownHostException) {
                                val cachedUsers = usersDao.get()
                                println("debug: cache users $cachedUsers")

                                withContext(Dispatchers.Main) {
                                    value = cachedUsers
                                    println("debug: $cachedUsers")
                                    theJob.complete()
                                }
                            }
                        }
                    }

                    theJob.invokeOnCompletion { throwable ->
                        if (throwable != null) {
                            println("debug: Throwable thrown: $throwable")
                                CoroutineScope(Dispatchers.IO + theJob).launch {
                                    val users = usersDao.get()
                                    withContext(Dispatchers.Main) {
                                        value = users
                                        //networkMapper.mapFromDtoList(userDtos) as MutableList<User>
                                        println("debug: $users")
                                        theJob.complete()
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    fun getUsersLocally() {

    }
    fun cancelJobs() {
        job?.cancel()
    }
}