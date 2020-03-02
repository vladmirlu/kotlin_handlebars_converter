package com.kotlinsample.converter.writer

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


@Component
class HandlebarsProvider {

    private var loader: TemplateLoader = ClassPathTemplateLoader("/templates", ".hbs")

    private var handlebars: Handlebars = Handlebars(loader)

    public fun getHandlebars():Handlebars {
       return this.handlebars
    }

    @Throws(IOException::class)
    fun getTemplate(templateName: String?): Template {
        return getHandlebars().compile(templateName)
    }

    fun getContext(model: JsonNode): Context {
        return Context
                .newBuilder(model)
                .resolver(JsonNodeValueResolver.INSTANCE,
                        JavaBeanValueResolver.INSTANCE,
                        FieldValueResolver.INSTANCE,
                        MapValueResolver.INSTANCE,
                        MethodValueResolver.INSTANCE)
                .build()
    }

}