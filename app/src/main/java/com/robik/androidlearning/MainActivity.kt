package com.robik.androidlearning

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val input = findViewById<EditText>(R.id.input)
        val radioGroup = findViewById<RadioGroup>(R.id.radio)

        //FileUtils.clearFile(this, "lab3.txt")

        btn.setOnClickListener {
            val id = radioGroup.checkedRadioButtonId

            when {
                input.text.isBlank() -> {
                    Toast.makeText(this, "Заповни питання!", Toast.LENGTH_SHORT).show()
                }
                id == -1 -> {
                    Toast.makeText(this, "Вибери відповідь!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val text = "${input.text} - ${
                        findViewById<RadioButton>(id).text
                    }"
                    val fragment1 = Fragment1()
                    val bundle = Bundle()
                    bundle.putString("result", text)
                    fragment1.arguments = bundle
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit() //.addToBackStack
                }
            }
        }
    }
}