package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView //private lateinit implies the var has been just declared and not initialized
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0 //We have initialized it here
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing TextViews
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)

        // Setting up number buttons
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        // Operation buttons
        val btnDiv: Button = findViewById(R.id.btnDivide)
        val btnMul: Button = findViewById(R.id.btnMultiply)
        val btnPls: Button = findViewById(R.id.btnPlus)
        val btnMin: Button = findViewById(R.id.btnMinus)

        // Special buttons
        val btnPercentage: Button = findViewById(R.id.btnPercent)
        val btnDot: Button = findViewById(R.id.btnDot)
        val btnEquals: Button = findViewById(R.id.btnEqualTo)
        val btnBack: Button = findViewById(R.id.btnBackspace)
        val btnC: Button = findViewById(R.id.btnClear)

        //click listeners for Numbers
        btn0.setOnClickListener { appendNumber(number="0") }
        btn1.setOnClickListener { appendNumber(number="1") }
        btn2.setOnClickListener { appendNumber(number="2") }
        btn3.setOnClickListener { appendNumber(number="3") }
        btn4.setOnClickListener { appendNumber(number="4") }
        btn5.setOnClickListener { appendNumber(number="5") }
        btn6.setOnClickListener { appendNumber(number="6") }
        btn7.setOnClickListener { appendNumber(number="7") }
        btn8.setOnClickListener { appendNumber(number="8") }
        btn9.setOnClickListener { appendNumber(number="9") }
        btnDot.setOnClickListener { appendNumber(number=".") }

        //click listners for Operators
        btnPls.setOnClickListener { setOperation("+") }
        btnMin.setOnClickListener { setOperation("-") }
        btnMul.setOnClickListener { setOperation("*") }
        btnDiv.setOnClickListener { setOperation("%") }

        //special buttons
        btnC.setOnClickListener { clearCalculator() }
        btnEquals.setOnClickListener { calculateResult() }
        btnBack.setOnClickListener { backSpace() }
    }
    private fun appendNumber(number: String){
        if(isNewOperation){
            resultTextView.text=number //when nothing is entered inside the result textView, just set it equal to number
            isNewOperation=false //after entering, its not a new number therefore false
        }else{
            resultTextView.text="${resultTextView.text}$number" //it will show the original number that was there and the new number that we have just entered now
        }
    }
    private fun setOperation(op: String){
        firstNumber=resultTextView.text.toString().toDouble() //takes the number and converts it to first string and then double
        operation=op
        isNewOperation=true
        previousCalculationTextView.text="$firstNumber$operation" //As soon as we press any operator, the number we entered will become the first number
    }
    private fun calculateResult(){
        try {
            val secondNumber: Double = resultTextView.text.toString().toDouble()
            val result: Double = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "%" -> firstNumber / secondNumber
                else -> secondNumber
            }
            previousCalculationTextView.text="$firstNumber$operation$secondNumber="
            resultTextView.text=result.toString()
            isNewOperation=true
        }catch (e: Exception){
            resultTextView.text="Error"
        }
    }
    private fun backSpace(){
        val currentText = resultTextView.text.toString()
        if (currentText.isNotEmpty()) {
            resultTextView.text = currentText.dropLast(1)
        }

        // If everything is deleted, reset to 0 and mark as new operation
        if (resultTextView.text.isEmpty()) {
            resultTextView.text = "0"
            isNewOperation = true
              val context = null
                Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearCalculator(){
        resultTextView.text="0"
        previousCalculationTextView.text=""
        firstNumber=0.0
        operation=""
        isNewOperation=true
    }
}
