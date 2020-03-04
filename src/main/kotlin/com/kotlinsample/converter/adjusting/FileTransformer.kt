package com.kotlinsample.converter.adjusting

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileReader
import javax.annotation.PostConstruct

/**
 * Transforms files from JSON format to relevant output format
 * */
@Component
class FileTransformer {

    @Value("\${input.data.folder}")
    lateinit var inputDataFolder: String

    @Value("\${output.file.calendar}")
    lateinit var calendarOutputFile: String

    @Value("\${output.file.security}")
    lateinit var securitiesOutputFile: String

    @Value("\${input.json.calendar}")
    lateinit var calendarJson: String

    @Value("\${input.json.security}")
    lateinit var securitiesJson: String

    @Autowired
    lateinit var fileConstructor: FileConstructor

    @Autowired
    lateinit var handlebarsProvider: HandlebarsProvider

    val jsonMapper = ObjectMapper()

    /**
     * Extract data from given CalendarInfo JSON file and applies Handlebars templates to data
     * */
    @PostConstruct
    fun transformCalendarInfo() {
        try {
            val jsonFile = File(inputDataFolder + calendarJson)
            val calendarInfos = jsonMapper.readValue(jsonFile, JsonNode::class.java)
            val template = handlebarsProvider.getTemplate(calendarOutputFile)
            val outputFile = fileConstructor.getOutputFile(calendarOutputFile)
            val handlebarsStr = template.apply(handlebarsProvider.getContext(calendarInfos, outputFile.length() == 0L))
            fileConstructor.writeIntoFile(handlebarsStr.toByteArray(), outputFile)
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    /**
     * Extract data from given securities JSON file and applies Handlebars templates to data
     * */
    @PostConstruct
    fun transformSecurities() {

        try {
            val jsonFile = File(inputDataFolder + securitiesJson)
            val jsonObject: JSONObject = JSONParser().parse(FileReader(jsonFile)) as JSONObject
            val securitiesJson = jsonObject["securities"] as JSONArray
            val securities = jsonMapper.readValue(securitiesJson.toJSONString(), JsonNode::class.java)
            val template = handlebarsProvider.getTemplate(securitiesOutputFile)
            val outputFile = fileConstructor.getOutputFile(securitiesOutputFile)
            val handlebarsStr = template.apply(handlebarsProvider.getContext(securities, outputFile.length() == 0L))
            fileConstructor.writeIntoFile(handlebarsStr.toByteArray(), outputFile)
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

}