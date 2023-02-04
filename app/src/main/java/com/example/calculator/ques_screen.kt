package com.example.calculator

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_ques_screen.*

class ques_screen : AppCompatActivity(), View.OnClickListener {
    var iv_image: ImageView? = null
    var progress_bar: ProgressBar? = null
    var selected_op: String? = null
    var selected_op_num: Int = 0
    var question: Question? = null
    var how_times_push_submit = 0
    var correctCounter=0

    var tv_progress: TextView? = null
    var ques: TextView? = null
    var op1: TextView? = null
    var op2: TextView? = null
    var op3: TextView? = null
    var op4: TextView? = null
    var submit: Button? = null
    var temp_obj = Questions()
    var ques_list = temp_obj.getQuestion()
    var ques_number = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ques_screen)
        iv_image = findViewById(R.id.image)
        progress_bar = findViewById(R.id.progress_bar)
        tv_progress = findViewById(R.id.tv_progress)
        ques = findViewById(R.id.question)
        op1 = findViewById(R.id.opOne)
        op2 = findViewById(R.id.opTwo)
        op3 = findViewById(R.id.opThree)
        op4 = findViewById(R.id.opFour)
        submit = findViewById(R.id.submit_button)
        op1?.setOnClickListener(this)
        op2?.setOnClickListener(this)
        op3?.setOnClickListener(this)
        op4?.setOnClickListener(this)
        submit?.setOnClickListener(this)




        setQuestion()


    }

    private fun setQuestion() {
        if (ques_number <= 12) {
            question = quesPicker()
            progress_bar?.progress = ques_number
            tv_progress?.text = "$ques_number/12"
            ques?.text = question?.ques
            op1?.text = question?.op1
            op2?.text = question?.op2
            op3?.text = question?.op3
            op4?.text = question?.op4
            iv_image?.setImageResource(question!!.img)
            deafaultOptions()
        }
        else{
            val mAlertDialog = AlertDialog.Builder(this) // This needs the activity's context
            val main=MainActivity()
            val name=main.name

            mAlertDialog.setMessage("Hey ${name} !! \n You have $correctCounter correct answer!! \n Congrats !! ")
            mAlertDialog.setPositiveButton("Again",DialogInterface.OnClickListener { dialog,
            which ->again()
            finish()
            })
            mAlertDialog.show()

        }




    }

    fun quesPicker(): Question {
        var rnd = 0
        if (ques_number >= 10) {
            do {
                rnd = (0..ques_list.size - 1).random()

            } while (ques_list[rnd].level != 3 || ques_list[rnd].asked == true)

        } else if (ques_number >= 4) {
            do {
                rnd = (0..ques_list.size - 1).random()

            } while (ques_list[rnd].level != 2 || ques_list[rnd].asked == true)

        } else {
            do {
                rnd = (0..ques_list.size - 1).random()

            } while (ques_list[rnd].level != 1 || ques_list[rnd].asked == true)

        }
        ques_list[rnd].asked = true
        return ques_list[rnd]
    }


    fun deafaultOptions() {
        val options = ArrayList<TextView>()
        opOne.let { options.add(0, it) }
        opTwo.let { options.add(1, it) }
        opThree.let { options.add(2, it) }
        opFour.let { options.add(3, it) }
        for (op in options) {
            op.setTextColor(Color.parseColor("#FFD000"))
            op.typeface = Typeface.DEFAULT
            op.background = ContextCompat.getDrawable(this, R.drawable.unselected)
        }
    }

    fun selectedOpView(t: TextView, selectedNum: Int) {
        selected_op_num = selectedNum
        deafaultOptions()
        t.setTypeface(t.typeface, Typeface.BOLD)
        t.background = ContextCompat.getDrawable(this, R.drawable.edge)

    }


    override fun onClick(v: View?) {

        if (how_times_push_submit % 2 == 0) {
            when (v?.id) {

                R.id.opOne -> op1.let { selectedOpView(opOne, 1) }
                R.id.opTwo -> op2.let { selectedOpView(opTwo, 2) }
                R.id.opThree -> op3.let { selectedOpView(opThree, 3) }
                R.id.opFour -> op4.let { selectedOpView(opFour, 4) }
                R.id.submit_button -> {
                    submit_button.text = "Next Question"
                    how_times_push_submit++
                    var correct_num = when (question!!.correct) {
                        op1?.text -> 1
                        op2?.text -> 2
                        op3?.text -> 3
                        op4?.text -> 4
                        else -> 0
                    }

                    if (selected_op_num == 0) {
                        answerview(correct_num, R.drawable.correct)
                        if (how_times_push_submit % 2 == 0) {
                            ques_number++

                            setQuestion()
                            submit?.text = "SUBMIT"
                        }

                    } else if (selected_op_num == correct_num) {
                        correctCounter++
                        answerview(correct_num, R.drawable.correct)
                        if (how_times_push_submit % 2 == 0) {
                            ques_number++
                            setQuestion()
                            submit?.text = "SUBMIT"
                        }

                    } else {

                        answerview(selected_op_num, R.drawable.wrong)
                        answerview(correct_num, R.drawable.correct)
                        if (how_times_push_submit % 2 == 0) {
                            ques_number++

                            setQuestion()
                            submit?.text = "SUBMIT"
                        }
                    }

                }

            }
        } else {
            when (v?.id) {
                R.id.submit_button -> {
                    submit_button.text = "Next Question"
                    how_times_push_submit++
                    var correct_num = when (question!!.correct) {
                        op1?.text -> 1
                        op2?.text -> 2
                        op3?.text -> 3
                        op4?.text -> 4
                        else -> 0
                    }

                    if (selected_op_num == 0) {

                        if (how_times_push_submit % 2 == 0) {
                            ques_number++
                            setQuestion()
                            submit?.text = "SUBMIT"
                        }

                    } else if (selected_op_num == correct_num) {

                        answerview(correct_num, R.drawable.correct)
                        if (how_times_push_submit % 2 == 0) {
                            ques_number++
                            setQuestion()
                            submit?.text = "SUBMIT"
                        }

                    } else {

                        answerview(selected_op_num, R.drawable.wrong)
                        answerview(correct_num, R.drawable.correct)
                        if (how_times_push_submit % 2 == 0) {
                            ques_number++

                            setQuestion()
                            submit?.text = "SUBMIT"
                        }
                    }
                    selected_op_num=0
                }


            }

        }
    }

    private fun answerview(answer: Int, Drawable: Int) {
        when (answer) {
            1 -> op1?.background = ContextCompat.getDrawable(this, Drawable)
            2 -> op2?.background = ContextCompat.getDrawable(this, Drawable)
            3 -> op3?.background = ContextCompat.getDrawable(this, Drawable)
            4 -> op4?.background = ContextCompat.getDrawable(this, Drawable)

        }
    }
    fun again(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
