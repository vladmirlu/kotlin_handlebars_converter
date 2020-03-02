package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class Security(
    @SerializedName("asset")
    val asset: Asset,
    @SerializedName("maturityDate")
    val maturityDate: MaturityDate,
    @SerializedName("productType")
    val productType: String,
    @SerializedName("smallestDenomination")
    val smallestDenomination: SmallestDenomination,
    @SerializedName("underlyings")
    val underlyings: List<Underlying>
)