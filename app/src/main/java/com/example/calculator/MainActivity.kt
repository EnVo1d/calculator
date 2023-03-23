package com.example.calculator

import android.opengl.Visibility
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
        this.currentItem = item.toString()
        when(currentItem) {
            "Circle" -> {
                findViewById<TextView>(R.id.param).text = "R ="
                showHideAdditional(findViewById<TextView>(R.id.param2), false)
                showHideAdditional(findViewById<TextView>(R.id.editTextNumberDecimal2), false)
            }
            "Square" -> {
                findViewById<TextView>(R.id.param).text = "a ="
                showHideAdditional(findViewById<TextView>(R.id.param2), false)
                showHideAdditional(findViewById<TextView>(R.id.editTextNumberDecimal2), false)
            }
            "Rectangle" -> {
                findViewById<TextView>(R.id.param).text = "a ="
                findViewById<TextView>(R.id.param2).text = "b ="
                showHideAdditional(findViewById<TextView>(R.id.param2), true)
                showHideAdditional(findViewById<TextView>(R.id.editTextNumberDecimal2), true)
            }
            else -> {
                return
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        return
    }

    private fun showHideAdditional(view: View, flag: Boolean) {
        view.visibility = if (flag){
            View.VISIBLE
        } else{
            View.INVISIBLE
        }
    }

    fun calculate(view: View) {
        val editTextValue = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString().toDoubleOrNull()
            ?: return;
        if(editTextValue == 0.0)
            return;
        var res: Double = 0.0
        res = when(currentItem) {
            "Circle" -> {
                ((PI * editTextValue.pow(2.0))*10).roundToInt() / 10.0
            }
            "Square" -> {
                ((editTextValue.pow(2.0))*10).roundToInt() / 10.0
            }
            "Rectangle" -> {
                val additional = findViewById<EditText>(R.id.editTextNumberDecimal2).text.toString().toDoubleOrNull()
                    ?: return
                if(additional == 0.0) return
                ((editTextValue * additional)*10).roundToInt() / 10.0
            }
            else -> {
                return
            }
        }

        findViewById<TextView>(R.id.result).text = res.toString()
    }
}