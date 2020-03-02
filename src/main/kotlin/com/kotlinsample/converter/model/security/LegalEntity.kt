package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class LegalEntity(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("legalEntityId")
    val legalEntityId: String,
    @SerializedName("shortName")
    val shortName: String
)