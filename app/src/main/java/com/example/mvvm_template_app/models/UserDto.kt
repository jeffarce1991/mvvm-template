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
    val address: AddressDto,
    @Expose
    @SerializedName("company")
    val company: CompanyDto,
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
    constructor(): this(-1,AddressDto(), CompanyDto(), "sample@gmail.com",  "Squall Leonheart", "","",    "")
}

data class AddressDto(
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("street")
    val street: String,
    @Expose
    @SerializedName("suite")
    val suite: String,
    @Expose
    @SerializedName("zipcode")
    val zipcode: String,
    @Expose
    @SerializedName("geo")
    val geo: GeoDto
) {
    constructor(): this("", "", "", "",GeoDto())
}

data class CompanyDto(
    @Expose
    @SerializedName("bs")
    val bs: String,
    @Expose
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @Expose
    @SerializedName("name")
    val name: String
) {
    constructor(): this("", "", "")
}

data class GeoDto(
    @Expose
    @SerializedName("lat")
    val lat: String,
    @Expose
    @SerializedName("lng")
    val lng: String
) {
    constructor(): this("", "")
}

