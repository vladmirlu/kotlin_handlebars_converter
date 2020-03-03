package com.kotlinsample.converter.adjusting

import com.fasterxml.jackson.databind.JsonNode
import com.github.jknack.handlebars.Context
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.context.FieldValueResolver
import com.github.jknack.handlebars.context.JavaBeanValueResolver
import com.github.jknack.handlebars.JsonNodeValueResolver
import com.github.jknack.handlebars.context.MapValueResolver
import com.github.jknack.handlebars.context.MethodValueResolver
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.TemplateLoader
import org.springframework.stereotype.Component
import java.io.IOException

/**
 * Provides Handlebars templates functionality
 * */
@Component
class HandlebarsProvider {

    /**
     *Loads relevant handlebars template by name
     * @param templateName handlebar template name
     * @return handlebars template
     * @throws IOException when load process crashes
     * */
    @Throws(IOException::class)
    fun getTemplate(templateName: String?): Template {

        val loader: TemplateLoader = ClassPathTemplateLoader("/templates", ".hbs")
        return Handlebars(loader).compile(templateName)
    }

    /**
     * Builds, adjusts, and returns handlebars context
     * @param model object to transfer into handlebar template
     * @param addHeaders marker for adding headers into handlebar template
     * @return returns handlebars context
     * @throws IOException when adjusting process crashes
     * */
    @Throws(IOException::class)
    fun getContext(model: JsonNode, addHeaders:Boolean): Context {
        return Context
                .newBuilder(model)
                .combine("addHeaders", addHeaders)
                .resolver(
                        JsonNodeValueResolver.INSTANCE,
                        JavaBeanValueResolver.INSTANCE,
                        FieldValueResolver.INSTANCE,
                        MapValueResolver.INSTANCE,
                        MethodValueResolver.INSTANCE
                ).build()
    }
}