package com.example.mycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var numZeroBtn: Button
    private lateinit var numOneBtn: Button
    private lateinit var numTwoBtn: Button
    private lateinit var numThreeBtn: Button
    private lateinit var numFourBtn: Button
    private lateinit var numFiveBtn: Button
    private lateinit var numSixBtn: Button
    private lateinit var numSevenBtn: Button
    private lateinit var numEightBtn: Button
    private lateinit var numNineBtn: Button

    private lateinit var operDivBtn: Button
    private lateinit var operMultBtn: Button
    private lateinit var operSubtracBtn: Button
    private lateinit var operAddBtn: Button
    private lateinit var operResultBtn: Button

    private lateinit var resetBtn: Button

    private lateinit var textToShow: TextView

    private val errorMessage = "Error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        setOnClickActions()
    }

    private fun initialization(){
        numZeroBtn = findViewById(R.id.num_zero_btn)
        numOneBtn = findViewById(R.id.num_one_bnt)
        numTwoBtn = findViewById(R.id.num_two_btn)
        numThreeBtn = findViewById(R.id.num_three_btn)
        numFourBtn = findViewById(R.id.num_four_btn)
        numFiveBtn = findViewById(R.id.num_five_btn)
        numSixBtn = findViewById(R.id.num_six_btn)
        numSevenBtn = findViewById(R.id.num_seven_btn)
        numEightBtn = findViewById(R.id.num_eight_btn)
        numNineBtn = findViewById(R.id.num_nine_btn)

        operDivBtn = findViewById(R.id.operation_div_btn)
        operAddBtn = findViewById(R.id.operation_add_btn)
        operMultBtn = findViewById(R.id.operation_mult_btn)
        operResultBtn = findViewById(R.id.operation_result_btn)
        operSubtracBtn = findViewById(R.id.operation_subtrac_btn)

        resetBtn = findViewById(R.id.reset_btn)

        textToShow = findViewById(R.id.text_view)
    }

    private fun setOnClickActions(){
        numZeroBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("0")
        }
        numOneBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("1")
        }
        numTwoBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("2")
        }
        numThreeBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("3")
        }
        numFourBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("4")
        }
        numFiveBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("5")
        }
        numSixBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("6")
        }
        numSevenBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("7")
        }
        numEightBtn.setOnClickListener{
            clearFieldIfItOnlyZeroOrError()
            textToShow.append("8")
        }
        numNineBtn.setOnClickListener{
            if(textToShow.text == "0")
                textToShow.text = ""
            if(textToShow.text == errorMessage)
                textToShow.text = ""
            textToShow.append("9")
        }
        operDivBtn.setOnClickListener{
            deleteLastCharIfItOperation()
            if(textToShow.text == errorMessage)
                textToShow.text = ""
            textToShow.append("/")
        }
        operMultBtn.setOnClickListener{
            if(textToShow.text.takeLast(1) == "/" ||
                textToShow.text.takeLast(1) == "*" ||
                textToShow.text.takeLast(1) == "+" ||
                textToShow.text.takeLast(1) == "-") {
                textToShow.text = textToShow.text.dropLast(1)
            }
            if(textToShow.text == errorMessage)
                textToShow.text = ""
            textToShow.append("*")
        }
        operSubtracBtn.setOnClickListener{
            deleteLastCharIfItOperation()
            if(textToShow.text == errorMessage)
                textToShow.text = ""
            textToShow.append("-")
        }
        operAddBtn.setOnClickListener{
            deleteLastCharIfItOperation()
            if(textToShow.text == errorMessage)
                textToShow.text = ""
            textToShow.append("+")
        }
        operResultBtn.setOnClickListener{
            deleteLastCharIfItOperation()
            textToShow.text = countTheExpression()
        }
        resetBtn.setOnClickListener{
            textToShow.text = "0"
        }
    }

    private fun countTheExpression(): String{
        val input = textToShow.text
        val dataNum = input.split("/", "*", "+", "-")
        val dataOperTemp = input.split("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        val dataOper = mutableListOf<String>()
        dataOperTemp.forEach{
            if(it != "") dataOper.add(it)
        }
        var result: Int = dataNum[0].toInt()
        for(i in 1 until dataNum.size){
            result = when(dataOper[i-1]){
                "/" ->  if(dataNum[i].toInt() != 0) result/dataNum[i].toInt() else return errorMessage
                "*" -> result*dataNum[i].toInt()
                "+" -> result+dataNum[i].toInt()
                "-" -> result-dataNum[i].toInt()
                else -> result
            }
        }
        return result.toString()
    }

    private fun clearFieldIfItOnlyZeroOrError(){
        if(textToShow.text == "0")
            textToShow.text = ""
        if(textToShow.text == errorMessage)
            textToShow.text = ""
    }

    private fun deleteLastCharIfItOperation(){
        if(textToShow.text.takeLast(1) == "/" ||
            textToShow.text.takeLast(1) == "*" ||
            textToShow.text.takeLast(1) == "+" ||
            textToShow.text.takeLast(1) == "-") {
            textToShow.text = textToShow.text.dropLast(1)
        }
    }
}


