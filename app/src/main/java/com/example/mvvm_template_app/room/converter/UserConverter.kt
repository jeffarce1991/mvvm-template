package com.example.mvvm_template_app.room.converter

import androidx.room.TypeConverter
import com.example.mvvm_template_app.models.Address
import com.example.mvvm_template_app.models.Company
import com.example.mvvm_template_app.models.Geo
import com.example.mvvm_template_app.utils.ConverterUtil.deserialise
import com.example.mvvm_template_app.utils.ConverterUtil.serialise

public class UserConverter {
    private fun UserConverter() {}

    @TypeConverter
    fun fromAddress(address: Address): String {
        return serialise(address)
    }

    @TypeConverter
    fun toAddress(serialised: String?): Address {
        return deserialise(
            serialised!!,
            Address::class.java
        )
    }

    @TypeConverter
    fun fromCompany(company: Company): String {
        return serialise(company)
    }

    @TypeConverter
    fun toCompany(serialised: String?): Company {
        return deserialise(
            serialised!!,
            Company::class.java
        )
    }
    @TypeConverter
    fun fromGeo(geo: Geo): String {
        return serialise(geo)
    }

    @TypeConverter
    fun toGeo(serialised: String?): Geo {
        return deserialise(
            serialised!!,
            Geo::class.java
        )
    }
}