package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class MaxOrderValue(
    @SerializedName("value")
    val value: String
)