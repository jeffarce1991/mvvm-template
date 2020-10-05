package com.example.mvvm_template_app.viewmodels

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_template_app.models.NicePlace
import com.example.mvvm_template_app.repositories.NicePlaceRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivityViewModel: ViewModel() {


    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown: $exception")
    }

    private var mNicePlaces: MutableLiveData<List<NicePlace>>? = null
    private lateinit var mRepo: NicePlaceRepository
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    fun init() {

        if (mNicePlaces != null) {
            return
        }
        mRepo = NicePlaceRepository.getInstance()

        mNicePlaces = mRepo.getNicePlaces()

    }

    suspend fun  addNewValue(nicePlace: NicePlace) {
            delay(1000)
            val currentPlaces: MutableList<NicePlace> = mNicePlaces!!.value as MutableList<NicePlace>
            currentPlaces.add(nicePlace)
            mNicePlaces!!.postValue(currentPlaces)
            mIsUpdating.postValue(false)

    }

    fun getNicePlaces() : MutableLiveData<List<NicePlace>>? {
        return mNicePlaces
    }

    fun getIsUpdating(): LiveData<Boolean?>? {
        return mIsUpdating
    }
}