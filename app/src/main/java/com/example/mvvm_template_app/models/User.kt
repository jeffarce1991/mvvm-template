package com.example.mvvm_template_app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
class User(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "address")
    val address: Address,

    @ColumnInfo(name = "company")
    val company: Company,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "website")
    val website: String
) {
    constructor(): this(-1, Address(), Company(), "sample@gmail.com", "Squall Leonheart", "","",    "")
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
