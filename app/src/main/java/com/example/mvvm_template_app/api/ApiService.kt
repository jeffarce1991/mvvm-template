package com.example.mvvm_template_app.api

import com.example.mvvm_template_app.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): User

    @GET("users")
    suspend fun getUsers(): MutableList<User>
}