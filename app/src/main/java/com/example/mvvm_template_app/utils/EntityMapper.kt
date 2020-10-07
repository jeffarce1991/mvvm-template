package com.example.mvvm_template_app.utils

interface EntityMapper <Dto, Model>{

    fun mapFromDto(dto: Dto): Model

    fun mapToDto(model: Model): Dto
}