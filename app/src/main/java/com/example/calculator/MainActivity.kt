package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var currentItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.shapes)
        ArrayAdapter.createFromResource(
            this,
            R.array.shapes_array,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            this.currentItem = adapter.getItem(0).toString()
        }
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val item = parent.getItemAtPosition(pos)
        when(currentItem) {
            "Circle" -> {
                findViewById<TextView>(R.id.param).text = "R ="
            }
            "Square" -> {
                findViewById<TextView>(R.id.param).text = "a ="
            }
            else -> {
                return
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        return
    }

    fun calculate(view: View) {
        val editTextValue = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString().toDoubleOrNull()
            ?: return;
        if(editTextValue == 0.0)
            return;
        var res: Double = 0.0
        when(currentItem) {
            "Circle" -> {
                val res = ((PI * editTextValue.pow(2.0))*10).roundToInt() / 10.0
            }
            "Square" -> {
                res = ((editTextValue.pow(2.0))*10).roundToInt() / 10.0
            }
            else -> {
                return
            }
        }

        findViewById<TextView>(R.id.result).text = res.toString()
    }
}