package com.example.mvvm_template_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvm_template_app.models.UserDto
import com.example.mvvm_template_app.repositories.UserRepository

class UserViewModel : ViewModel() {
    private var _userId: MutableLiveData<String> = MutableLiveData()

    val userDto: LiveData<UserDto> = Transformations
        .switchMap(_userId) {
            UserRepository.getUser(it)
        }

    fun setUserId(userId: String) {
        val update = userId
        if (_userId.value == update) {
            return
        }
        _userId.value = update
    }

    fun cancelJobs() {
        UserRepository.canJobs()
    }
}