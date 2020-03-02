package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class PriceStep(
    @SerializedName("priceFrom")
    val priceFrom: PriceFrom,
    @SerializedName("priceTo")
    val priceTo: PriceTo,
    @SerializedName("tickSize")
    val tickSize: TickSize
)