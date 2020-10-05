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


data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    constructor(): this(Address(), Company(), "sample@gmail.com", -1, "", "","",    "")
}

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
) {
    constructor(): this("", Geo(), "", "", "")
}

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
) {
    constructor(): this("", "", "")
}

data class Geo(
    val lat: String,
    val lng: String
) {
    constructor(): this("", "")
}
