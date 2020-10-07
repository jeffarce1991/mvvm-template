package com.example.mvvm_template_app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/*

data class User (
    @Expose
    @SerializedName("email")
    val email: String? = null,

    @Expose
    @SerializedName("username")
    val username: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
    ) {
        override fun toString(): String {
            return "User(email=$email, username=$username, image=$image)"
        }


}
*/


data class UserDto(
    val id: Int,
    val address: Address,
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    constructor(): this(-1,Address(), Company(), "sample@gmail.com",  "Squall Leonheart", "","",    "")
}

