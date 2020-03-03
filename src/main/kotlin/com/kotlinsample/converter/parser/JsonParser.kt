package com.kotlinsample.converter.parser

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.jknack.handlebars.Template
import com.google.gson.Gson
import com.kotlinsample.converter.model.calendar.CalendarInfo
import com.kotlinsample.converter.model.security.Security
import com.kotlinsample.converter.writer.FileService
import com.kotlinsample.converter.writer.HandlebarsProvider
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct


@Component
class JsonParser {

    @Value("\${input.data.folder}")
    lateinit var inputDataFolder: String

    @Autowired
    lateinit var fileService: FileService

    @Autowired
    lateinit var handlebarsProvider: HandlebarsProvider

    private val gson = Gson()

    @PostConstruct
    @Throws(IOException::class)
    fun parseCalendarInfo(){
        var jsonFile = File(inputDataFolder + "CalendarInfo.json")
        val jsonMapper = ObjectMapper()
        var calendarInfos: List<CalendarInfo> = jsonMapper.readValue(jsonFile, object : TypeReference<List<CalendarInfo>>() {})


        calendarInfos.filter {cal -> !cal.marketDay}.forEach { c ->
            var calInfoJsonStr = gson.toJson(c)
            val jsonNode: JsonNode = ObjectMapper().readValue(calInfoJsonStr, JsonNode::class.java)
            val template: Template = handlebarsProvider.getTemplate("CalendarInfo.txt")

            var handlebarsStr = template.apply(handlebarsProvider.getContext(jsonNode))
            fileService.writeIntoFile(handlebarsStr.toByteArray())
        }
    }

    @PostConstruct
    @Throws(IOException::class)
    fun parseSecurities(){
        var jsonFile = File(inputDataFolder + "securities.json")
        var reader: Reader = FileReader(jsonFile)
        val parser = JSONParser()
        var jsonObject: JSONObject = parser.parse(reader) as JSONObject
        var securitiesJson = (jsonObject.get("securities")) as JSONArray
        securitiesJson.forEach{value -> println(gson.fromJson(value.toString(), Security::class.java)) }
    }

}