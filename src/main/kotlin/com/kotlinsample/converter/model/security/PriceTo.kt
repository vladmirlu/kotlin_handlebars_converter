package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class PriceTo(
    @SerializedName("value")
    val value: String
)