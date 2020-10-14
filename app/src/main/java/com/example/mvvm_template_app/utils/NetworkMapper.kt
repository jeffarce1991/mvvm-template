package com.example.mvvm_template_app.utils

import com.example.mvvm_template_app.models.*

class NetworkMapper:
    EntityMapper<UserDto, User> {

    override fun mapFromDto(dto: UserDto): User {
        return User(
            dto.id,
            Address(
                dto.address.city,
                dto.address.street,
                dto.address.suite,
                dto.address.zipcode,
                Geo(dto.address.geo.lat,
                    dto.address.geo.lng)
            ),
            Company(
                dto.company.bs,
                dto.company.catchPhrase,
                dto.company.name
            ),
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
            AddressDto(
                model.address.city,
                model.address.street,
                model.address.suite,
                model.address.zipcode,
                GeoDto(model.address.geo.lat,
                    model.address.geo.lng)
            ),
            CompanyDto(
                model.company.bs,
                model.company.catchPhrase,
                model.company.name
            ),
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