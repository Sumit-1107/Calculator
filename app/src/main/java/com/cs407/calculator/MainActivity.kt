package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextNumber1 = findViewById<EditText>(R.id.editTextNumber)
        val editTextNumber2 = findViewById<EditText>(R.id.editTextNumber2)
        val addButton = findViewById<Button>(R.id.button)
        val subtractButton = findViewById<Button>(R.id.button2)
        val multiplyButton = findViewById<Button>(R.id.button3)
        val divideButton = findViewById<Button>(R.id.button4)

        // set click listener
        addButton.setOnClickListener { performOperation(editTextNumber1, editTextNumber2, "+")}
        subtractButton.setOnClickListener { performOperation(editTextNumber1, editTextNumber2, "-") }
        multiplyButton.setOnClickListener { performOperation(editTextNumber1, editTextNumber2, "*") }
        divideButton.setOnClickListener { performOperation(editTextNumber1, editTextNumber2, "/") }
    }

    private fun performOperation(editText1: EditText, editText2: EditText, operation: String) {
        val num1Str = editText1.text.toString()
        val num2Str = editText2.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = num1Str.toIntOrNull()
        val num2 = num2Str.toIntOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid integers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    num1 / num2
                }
            }
            else -> 0
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtra("RESULT", result.toString())
        startActivity(intent)
    }
}