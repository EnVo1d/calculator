package com.example.calculator

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var currentItem: String
    lateinit var resultView: TextView
    lateinit var paramView: TextView
    lateinit var paramView2: TextView
    lateinit var paramView3: TextView
    lateinit var editTextView: EditText
    lateinit var editTextView2: EditText
    lateinit var editTextView3: EditText

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
        resultView = findViewById<TextView>(R.id.result)
        paramView = findViewById<TextView>(R.id.param)
        paramView2 = findViewById<TextView>(R.id.param2)
        paramView3 = findViewById<TextView>(R.id.param3)
        editTextView = findViewById<EditText>(R.id.editTextNumberDecimal)
        editTextView2 = findViewById<EditText>(R.id.editTextNumberDecimal2)
        editTextView3 = findViewById<EditText>(R.id.editTextNumberDecimal3)
        editTextView.addTextChangedListener(textWatcher)
        editTextView2.addTextChangedListener(textWatcher)
        editTextView3.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            resultView.text = ""
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        resultView.text = ""
        editTextView.text = null
        editTextView2.text = null
        editTextView3.text = null
        val item = parent.getItemAtPosition(pos)
        this.currentItem = item.toString()
        when(currentItem) {
            "Circle" -> {
                paramView.text = "R ="
                showHideAdditional(paramView2, false)
                showHideAdditional(editTextView2, false)
                showHideAdditional(paramView3, false)
                showHideAdditional(editTextView3, false)
            }
            "Square" -> {
                paramView.text = "a ="
                showHideAdditional(paramView2, false)
                showHideAdditional(editTextView2, false)
                showHideAdditional(paramView3, false)
                showHideAdditional(editTextView3, false)
            }
            "Rectangle" -> {
                paramView.text = "a ="
                paramView2.text = "b ="
                showHideAdditional(paramView2, true)
                showHideAdditional(editTextView2, true)
                showHideAdditional(paramView3, false)
                showHideAdditional(editTextView3, false)
            }
            "Triangle" -> {
                paramView.text = "a ="
                paramView2.text = "b ="
                paramView3.text = "c ="
                showHideAdditional(paramView2, true)
                showHideAdditional(editTextView2, true)
                showHideAdditional(paramView3, true)
                showHideAdditional(editTextView3, true)
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
        val editTextValue = editTextView.text.toString().toDoubleOrNull()
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
                val additional = editTextView2.text.toString().toDoubleOrNull()
                    ?: return
                if(additional == 0.0) return
                ((editTextValue * additional)*10).roundToInt() / 10.0
            }
            "Triangle" -> {
                val firstAdditional = editTextView2.text.toString().toDoubleOrNull()
                    ?: return
                if(firstAdditional == 0.0) return
                val secondAdditional = editTextView3.text.toString().toDoubleOrNull()
                    ?: return
                if(secondAdditional == 0.0) return
                val p = (editTextValue+firstAdditional+secondAdditional) / 2.0
                val s = sqrt(p*(p-editTextValue)*(p-firstAdditional)*(p-secondAdditional))
                (s*10).roundToInt() / 10.0
            }
            else -> {
                return
            }
        }

        findViewById<TextView>(R.id.result).text = res.toString()
    }
}