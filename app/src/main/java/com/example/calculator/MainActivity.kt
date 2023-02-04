package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    public var name:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart= findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            et_name?.text.let {
                val nameTaken=et_name.text.toString()
                val intent=Intent(this,ques_screen::class.java)
                startActivity(intent)
                finish()
                name=nameTaken.toString()
            }

        }
    }
}