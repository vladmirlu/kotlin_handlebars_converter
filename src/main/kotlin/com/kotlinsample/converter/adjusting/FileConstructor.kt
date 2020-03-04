package com.kotlinsample.converter.adjusting

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Constructs and provides files
 **/
@Component
class FileConstructor {

    @Value("\${output.data.folder}")
    lateinit var outputDataFolder: String

    /**
     * Customisable buffer size
     * */
    private val bufferSize = 7

    /**
     * Writes date into file via buffer. Moves writing offset to the end of the existing file using RandomAccessFile
     * @param byteArray total bytes quantity to write
     * @param outputFile file for writing byteArray
     * @throws IOException when writing file process crashes
     * */
    fun writeIntoFile(byteArray: ByteArray, outputFile:File) {

        val writingFile = RandomAccessFile(outputFile, "rw")
        try {
            writingFile.seek(outputFile.length())
            var writeLength = byteArray.size
            var offset = 0

            while (writeLength > 0) {
                if (bufferSize >= writeLength) {
                    writingFile.write(byteArray.copyOfRange(offset, offset + writeLength))
                    writeLength = 0
                } else {
                    writingFile.write(byteArray.copyOfRange(offset, offset + bufferSize))
                    writeLength -= bufferSize
                    offset += bufferSize
                }
                writingFile.seek(writingFile.filePointer)
            }
        } catch (ex: IOException) {
            throw RuntimeException(ex.message)
        } finally {
            writingFile.close()
        }
    }

    /**
     * Finds existing output directory and file or creates new if not
     * @param fileName name of file to write with extension
     * @return existing or new created file
     * @throws IOException when finding or creating file process crashes
     * */
    @Throws(IOException::class)
    fun getOutputFile(fileName: String):File {

        val outputDir = File(outputDataFolder)
        if (!outputDir.exists()) {
            Files.createDirectory(Paths.get(outputDataFolder));
        }
        val outputFile = File(outputDir.absolutePath + "/" + fileName)
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        return outputFile
    }
}