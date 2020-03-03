package com.kotlinsample.converter.writer

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException

import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Component
class FileService {

    @Value("\${output.data.folder}")
    lateinit var outputDataFolder: String

    @Value("\${output.data.calendar}")
    lateinit var outputDataCalendar: String

    @Value("\${output.data.security}")
    lateinit var outputDataSecurity: String

    private val bufferSize = 7

    public fun writeIntoFile(byteArray: ByteArray){

        var outputDir = File(outputDataFolder)
        if(!outputDir.exists()){
            Files.createDirectory(Paths.get(outputDataFolder));
        }
        var outputFile = File(outputDir.absolutePath + outputDataCalendar)
        if(!outputFile.exists()){
            outputFile.createNewFile()
        }
        var fileToWrite = RandomAccessFile(outputFile, "rw")

        try {
            fileToWrite.seek(outputFile.length())
            var writeLength = byteArray.size
            var offset = 0

            while (writeLength > 0) {
                if (bufferSize >= writeLength) {
                    fileToWrite.write(Arrays.copyOfRange(byteArray, offset, offset + writeLength))
                    writeLength = 0
                } else {
                    fileToWrite.write(Arrays.copyOfRange(byteArray, offset, offset + bufferSize))
                    writeLength -= bufferSize
                    offset += bufferSize
                }
                fileToWrite.seek(fileToWrite.filePointer)
            }
        } catch (ex: IOException) {
            throw RuntimeException(ex.message)
        } finally {
            fileToWrite.close()
        }
    }

}