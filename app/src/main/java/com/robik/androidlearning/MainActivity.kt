package com.robik.androidlearning

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        val result = findViewById<TextView>(R.id.result)
        val input = findViewById<EditText>(R.id.input)
        val radioGroup = findViewById<RadioGroup>(R.id.radio)

        btn.setOnClickListener {
            val id = radioGroup.checkedRadioButtonId

            when {
                input.text.isBlank() -> {
                    Toast.makeText(this, "Заповни питання!", Toast.LENGTH_SHORT).show()
                }
                id == -1 -> {
                    Toast.makeText(this, "Вибери відповідь!", Toast.LENGTH_SHORT).show()
                }
                else -> result.text = "${input.text} - ${
                    findViewById<RadioButton>(id).text
                }"
            }
        }
    }
}