package com.kotlinsample.converter.model.calendar


import com.google.gson.annotations.SerializedName

data class CalendarInfo(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("marketDay")
    val marketDay: Boolean
)