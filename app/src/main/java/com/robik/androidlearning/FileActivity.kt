package com.robik.androidlearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FileActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        val textView = findViewById<TextView>(R.id.text_from_file)
        textView.text = FileUtils.readFromFile(this, "lab3.txt")
        if(textView.text.isNullOrEmpty())
            Toast.makeText(this, "Файл пустий!", Toast.LENGTH_SHORT).show()
        //Log.d("tag", "onCreate: ${FileUtils.readFromFile(this, "lab3.txt")}")
    }

}