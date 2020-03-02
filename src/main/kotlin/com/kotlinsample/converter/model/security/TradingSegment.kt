package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class TradingSegment(
    @SerializedName("id")
    val id: String,
    @SerializedName("maxOrderValue")
    val maxOrderValue: MaxOrderValue
)