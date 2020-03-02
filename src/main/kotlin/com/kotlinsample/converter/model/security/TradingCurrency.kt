package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class TradingCurrency(
    @SerializedName("code")
    val code: String,
    @SerializedName("precision")
    val precision: Int
)