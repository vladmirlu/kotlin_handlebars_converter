package com.kotlinsample.converter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct


@SpringBootApplication
class KotlinHandlebarsFileConverterApplication

fun main(args: Array<String>) {
	runApplication<KotlinHandlebarsFileConverterApplication>(*args)
}

