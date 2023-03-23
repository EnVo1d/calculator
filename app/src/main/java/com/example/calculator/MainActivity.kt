package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view: View) {
        val editTextValue = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString().toDoubleOrNull()
            ?: return;
        if(editTextValue == 0.0)
            return;
        val res = ((PI * editTextValue.pow(2.0))*10).roundToInt() / 10.0;
        findViewById<TextView>(R.id.result).text = res.toString();
    }
}