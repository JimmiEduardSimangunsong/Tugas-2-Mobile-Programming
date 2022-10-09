package com.example.tugas2mpr

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SkorPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skor_page)
        var skor = intent.getIntExtra("skor", 0)
        var resultTextView =   findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = "Skor kamu adalah $skor"
        resultTextView.textSize = 30f
        resultTextView.setTextColor(Color.BLUE)

        var buttonRetry = findViewById<TextView>(R.id.button1)
        buttonRetry.setOnClickListener {
//            back to main activity
            finish()
        }
    }
}