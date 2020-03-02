package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class Asset(
    @SerializedName("isin")
    val isin: String,
    @SerializedName("issuer")
    val issuer: Issuer,
    @SerializedName("quantityStep")
    val quantityStep: Int,
    @SerializedName("tradable")
    val tradable: List<Tradable>
)