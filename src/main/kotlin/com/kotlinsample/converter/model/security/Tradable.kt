package com.kotlinsample.converter.model.security


import com.google.gson.annotations.SerializedName

data class Tradable(
    @SerializedName("firstTradingDate")
    val firstTradingDate: FirstTradingDate,
    @SerializedName("initialReferencePrice")
    val initialReferencePrice: InitialReferencePrice,
    @SerializedName("isTradable")
    val isTradable: Boolean,
    @SerializedName("lastTradingDate")
    val lastTradingDate: LastTradingDate,
    @SerializedName("priceStepGroup")
    val priceStepGroup: PriceStepGroup,
    @SerializedName("priceType")
    val priceType: String,
    @SerializedName("tradedAssetId")
    val tradedAssetId: Int,
    @SerializedName("tradingCurrency")
    val tradingCurrency: TradingCurrency,
    @SerializedName("tradingSegment")
    val tradingSegment: TradingSegment
)