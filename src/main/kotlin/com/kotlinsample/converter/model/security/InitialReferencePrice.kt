package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class InitialReferencePrice(
    @SerializedName("value")
    val value: String
)