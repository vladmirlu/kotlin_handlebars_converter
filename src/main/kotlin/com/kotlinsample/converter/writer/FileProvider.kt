package com.kotlinsample.converter.writer

import org.springframework.stereotype.Component
import java.io.File




@Component
class FileProvider {

    fun getDirectory(directoryPath: String): File {
        return File(directoryPath)
    }
}