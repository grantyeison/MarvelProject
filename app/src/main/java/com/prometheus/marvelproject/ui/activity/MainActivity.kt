package com.prometheus.marvelproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.prometheus.marvelproject.R
import com.prometheus.marvelproject.application.MarvelProjectApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    var response = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Arithmetic Operations click
        btnAdd.setOnClickListener(this)
        btnLess.setOnClickListener(this)
        btnMult.setOnClickListener(this)
        btnDiv.setOnClickListener(this)

        //Number Click
        btnNumber_0.setOnClickListener(this)
        btnNumber_1.setOnClickListener(this)
        btnNumber_2.setOnClickListener(this)
        btnNumber_3.setOnClickListener(this)
        btnNumber_4.setOnClickListener(this)
        btnNumber_5.setOnClickListener(this)
        btnNumber_6.setOnClickListener(this)
        btnNumber_7.setOnClickListener(this)
        btnNumber_8.setOnClickListener(this)
        btnNumber_9.setOnClickListener(this)

        //Group symbols click
        btnParenthesisOpen.setOnClickListener(this)
        btnParenthesisClose.setOnClickListener(this)

        //result Click
        btnResponse.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val operation: String
        when (p0) {
            btnAdd -> {
                operation = operationText.text.toString() + "+"
                operationText.setText(operation)
            }
            btnLess -> {
                operation = operationText.text.toString() + "-"
                operationText.setText(operation)
            }
            btnMult -> {
                operation = operationText.text.toString() + "X"
                operationText.setText(operation)
            }
            btnDiv -> {
                operation = operationText.text.toString() + "/"
                operationText.setText(operation)
            }
            btnNumber_0 -> {
                operation = operationText.text.toString() + "0"
                operationText.setText(operation)
            }
            btnNumber_1 -> {
                operation = operationText.text.toString() + "1"
                operationText.setText(operation)
            }
            btnNumber_2 -> {
                operation = operationText.text.toString() + "2"
                operationText.setText(operation)
            }
            btnNumber_3 -> {
                operation = operationText.text.toString() + "3"
                operationText.setText(operation)
            }
            btnNumber_4 -> {
                operation = operationText.text.toString() + "4"
                operationText.setText(operation)
            }
            btnNumber_5 -> {
                operation = operationText.text.toString() + "5"
                operationText.setText(operation)
            }
            btnNumber_6 -> {
                operation = operationText.text.toString() + "6"
                operationText.setText(operation)
            }
            btnNumber_7 -> {
                operation = operationText.text.toString() + "7"
                operationText.setText(operation)
            }
            btnNumber_8 -> {
                operation = operationText.text.toString() + "8"
                operationText.setText(operation)
            }
            btnNumber_9 -> {
                operation = operationText.text.toString() + "9"
                operationText.setText(operation)
            }
            btnParenthesisOpen -> {
                operation = operationText.text.toString() + "("
                operationText.setText(operation)
            }
            btnParenthesisClose -> {
                operation = operationText.text.toString() + ")"
                operationText.setText(operation)
            }
            btnResponse -> if (checkOperation()) {
                operation = operationText.text.toString()
                response = calculateResponse(operation, false)
                val intent = Intent(this, ResponseActivity::class.java)
                println(">>>>>> Response:$response")
                intent.putExtra("response", response)
                startActivity(intent)
            }
        }
    }


    private fun calculateResponse(operation: String, parenOpen: Boolean): Int {
        var response = 0
        var i = 0
        var op = '+'
        while (i < operation.length) {
            val current = operation[i]
            if (!isDigit(current)) {
                when (current) {
                    '(' -> {
                        val sub = operation.substring(i + 1)
                        val value = calculateResponse(sub, true)
                        when (op) {
                            '+' -> response = response + value
                            '-' -> response = response - value
                            'X' -> response = response * value
                            '/' -> response = response / value
                        }
                        i = i + sub.indexOf(')') + i
                    }
                    ')' -> if (parenOpen)
                        return response
                    else
                        Toast.makeText(this, R.string.toastMissParenthesis, Toast.LENGTH_SHORT).show()
                    else -> if (op == '!')
                        op = current
                    else {
                        if (op == '-') {
                            when (current) {
                                '+' -> op = '-'
                                '-' -> op = '+'
                                'X' -> {
                                }
                                '/' -> {
                                }
                            }//op = '!';     caso -X
                            //op = '!';     caso -/

                        }
                    }
                }
            } else {
                when (op) {
                    '+' -> {
                        response = response + Integer.parseInt("" + current)
                        op = '!'
                    }
                    '-' -> {
                        response = response - Integer.parseInt("" + current)
                        op = '!'
                    }
                    'X' -> {
                        response = response * Integer.parseInt("" + current)
                        op = '!'
                    }
                    '/' -> {
                        response = response / Integer.parseInt("" + current)
                        op = '!'
                    }
                    '!' -> {
                        response = response * 10 + Integer.parseInt("" + current)
                    }
                }
            }
            i++
        }
        return response
    }

    private fun isDigit(current: Char): Boolean {
        return if (current == '0' || current == '1' || current == '2' || current == '3' || current == '4' || current == '5' || current == '6' || current == '7' || current == '8' || current == '9') true else false
    }

    fun checkOperation(): Boolean {
        var parenthesis = false
        val operation = operationText.text.toString()
        var complete = 0
        for (i in 0 until operation.length) {
            if (operation[i] == '(')
                complete++
            if (operation[i] == ')')
                complete--
        }
        if (complete == 0)
            parenthesis = true
        else {
            if (complete > 0)
                Toast.makeText(this, R.string.toastLeftParenthesis, Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, R.string.toastMissParenthesis, Toast.LENGTH_SHORT).show()
        }

        return parenthesis
    }

}
