package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class PriceStepGroup(
    @SerializedName("code")
    val code: Any,
    @SerializedName("groupId")
    val groupId: String,
    @SerializedName("priceStepList")
    val priceStepList: List<PriceStep>
)