package com.example.mvvm_template_app.utils

import com.example.mvvm_template_app.models.UserDto
import com.example.mvvm_template_app.models.User

class NetworkMapper:
    EntityMapper<UserDto, User> {

    override fun mapFromDto(dto: UserDto): User {
        return User(
            dto.id,
            dto.address,
            dto.company,
            dto.email,
            dto.name,
            dto.phone,
            dto.username,
            dto.website
            )
    }

    override fun mapToDto(model: User): UserDto {
        return UserDto(
            model.id,
            model.address,
            model.company,
            model.email,
            model.name,
            model.phone,
            model.username,
            model.website
        )
    }

    fun mapFromDtoList(entities: List<UserDto>): List<User>{
        return entities.map { mapFromDto(it) }
    }
}