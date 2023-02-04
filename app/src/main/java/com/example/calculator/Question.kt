package com.example.calculator

data class Question(
    val ques:String ,val op1:String,val op2:String,val op3:String,val op4:String
    ,val correct:String,var asked:Boolean,val img:Int,val level:Int
)
