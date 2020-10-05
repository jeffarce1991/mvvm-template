package com.example.mvvm_template_app.models

data class NicePlace(
    var imageUrl: String,
    var title: String) {

    constructor(): this("", "")
}