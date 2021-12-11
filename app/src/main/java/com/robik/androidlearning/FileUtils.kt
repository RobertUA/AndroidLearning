package com.robik.androidlearning

import android.content.Context
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

object FileUtils {
    fun readFromFile(context: Context, fileName: String) : String
    {
        val fileInputStream: FileInputStream? = context.openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text + '\n')
        }
        return stringBuilder.toString()
    }
    fun writeToFile(context: Context, text: String, fileName: String)
    {
        val fileOutputStream: FileOutputStream
        try
        {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
            fileOutputStream.write((text+'\n').toByteArray())
            fileOutputStream.close()
            Toast.makeText(context, "Запис в файл - успішна", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception)
        {
            Toast.makeText(context, "Помилка запису в файл!", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }
    fun clearFile(context: Context, fileName:String)
    {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write("".toByteArray())
            fileOutputStream.close()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }
}