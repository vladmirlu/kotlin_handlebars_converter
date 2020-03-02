package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class Issuer(
    @SerializedName("bic")
    val bic: String,
    @SerializedName("bpId")
    val bpId: String,
    @SerializedName("legalEntity")
    val legalEntity: LegalEntity
)