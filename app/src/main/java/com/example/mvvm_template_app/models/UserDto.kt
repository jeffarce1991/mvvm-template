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
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("address")
    val address: Address,
    @Expose
    @SerializedName("company")
    val company: Company,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("website")
    val website: String
) {
    constructor(): this(-1,Address(), Company(), "sample@gmail.com",  "Squall Leonheart", "","",    "")
}

