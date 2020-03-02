package com.kotlinsample.converter.parser

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.kotlinsample.converter.model.calendar.CalendarInfo
import com.kotlinsample.converter.model.security.Security
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.Reader
import java.util.*
import javax.annotation.PostConstruct
import kotlin.collections.ArrayList


@Component
class JsonParser {

    @Value("\${json.data.folder}")
    lateinit var jsonDataFolder: String

    private val gson = Gson()

    @PostConstruct
    @Throws(IOException::class)
    fun parseCalendarInfo(){
        var jsonFile = File(jsonDataFolder + "CalendarInfo.json")
        val jsonMapper = ObjectMapper()
        var calendarInfos: List<CalendarInfo> = jsonMapper.readValue(jsonFile, object : TypeReference<List<CalendarInfo>>() {})
        calendarInfos.forEach{value -> println(value) }
    }

    @PostConstruct
    @Throws(IOException::class)
    fun parseSecurities(){
        var jsonFile = File(jsonDataFolder + "securities.json")
        var reader: Reader = FileReader(jsonFile)
        val parser = JSONParser()
        var jsonObject: JSONObject = parser.parse(reader) as JSONObject
        var securitiesJson = (jsonObject.get("securities")) as JSONArray
        securitiesJson.forEach{value -> println(gson.fromJson(value.toString(), Security::class.java)) }
    }

}