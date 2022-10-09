package com.example.tugas2mpr

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var buttons: Array<Button>

    lateinit var resultTextView: TextView

    private var arrayNumber = arrayListOf(0, 0, 0, 0)


    private var firstButtonPressed = false
    private var firstValue = 0
    private var secondValue = 0
    var currentSkor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4)
        )

        for (button in buttons)
            button.setOnClickListener()
            { view: View ->
                checkButton(view)
            }

        resultTextView = findViewById(R.id.resultTextView)
        timerHandler()
        generateQuestion()

    }

    override fun onRestart() {
        super.onRestart()
        currentSkor = 0
        resultTextView.text = ""
        timerHandler()
        generateQuestion()
    }

    private fun timerHandler(){
        val timer = object : CountDownTimer(60000, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                findViewById<ProgressBar>(R.id.progressBar).progress = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                for (button in buttons)
                    button.isEnabled = false
                val skorPage = Intent(this@MainActivity, SkorPage::class.java)
                skorPage.putExtra("skor", currentSkor)
                startActivity(skorPage)
            }
        }
        timer.start()
    }

    private fun generateQuestion()
    {

        for (button in buttons)
        {
            button.isEnabled = true
        }
        firstButtonPressed = false
        firstValue = 0
        secondValue = 0
        val random1 = Random(System.currentTimeMillis()).nextInt(10, 50)
        val randomGenerator = Random(System.currentTimeMillis())

//        var result = randomGenerator.nextInt(20, 50);
        val buttonA = randomGenerator.nextInt(0, random1-1)
        val buttonB = random1 - buttonA
        var buttonC = randomGenerator.nextInt(0, random1-1)
        val buttonD = random1-buttonC

        println(buttonA)
        println(buttonB)
        println(buttonC)
        println(buttonD)

        val arrayInt = arrayOf(0, 1, 2, 3)
        arrayInt.shuffle(randomGenerator)

        buttons[arrayInt[0]].text = buttonA.toString()
        buttons[arrayInt[1]].text = buttonB.toString()

        buttons[arrayInt[2]].text = buttonC.toString()
        buttons[arrayInt[3]].text = buttonD.toString()
        arrayNumber = arrayListOf(buttonA, buttonB, buttonC, buttonD)
    }

    private fun checkButton(view : View)
    {
        val buttonPressed = view as Button

        if (firstButtonPressed)
        {
            secondValue = buttonPressed.text.toString().toInt()
            arrayNumber.remove(secondValue)
            checkAnswer()
//            delay 1 second

            generateQuestion()
            //validator answer
        }
        else
        {
            firstValue = buttonPressed.text.toString().toInt()
            firstButtonPressed = true
            buttonPressed.isEnabled = false
            arrayNumber.remove(firstValue)
            //wait for another button
        }
    }

    private fun checkAnswer() {
        var result = 0
        for (num in arrayNumber)
            result += num

        if (firstValue + secondValue == result)
        {
            currentSkor += 1
            resultTextView.text = "Benar"
            resultTextView.setTextColor(Color.GREEN)
            resultTimer.start()
        }
        else
        {
            resultTextView.text = "Salah"
            resultTextView.setTextColor(Color.RED)
            resultTimer.start()
        }
    }

    private var resultTimer = object : CountDownTimer(2000, 1000)
    {
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            resultTextView.text = ""
        }
    }
}