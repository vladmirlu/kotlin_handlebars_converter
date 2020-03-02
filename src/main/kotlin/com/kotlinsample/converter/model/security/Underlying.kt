package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class Underlying(
    @SerializedName("isin")
    val isin: String
)