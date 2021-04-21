package ru.startandroid.develop.kotlin_lesson

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.sql.Time
import java.util.*

class MainActivity : Activity() {

    private var imageSemafor:ImageView? = null
    private var imageArray:IntArray = intArrayOf(R.drawable.semafor_red,R.drawable.semafor_yellow,R.drawable.semafor_green)
    private var counter:Int = 0
    private var timer: Timer? = null
    private var is_run:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageSemafor = findViewById(R.id.ivSemafor)

    }

    fun onClickStartStop(view: View) {
        view as ImageButton
        if(!is_run)
        {
            timer = Timer()
            view.setImageResource(R.drawable.button_stop)
            start_stop()
            is_run = true
        }
        else
        {
            timer?.cancel()
            view.setImageResource(R.drawable.button_start)
            imageSemafor?.setImageResource(R.drawable.semafor_grey)
            is_run = false
            counter = 0
        }
    }
    fun start_stop(){
        timer?.schedule(object :TimerTask(){
            override fun run() {
                runOnUiThread{
                    imageSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if(counter == 3) counter = 0
                }
            }

        }, 0,1000)
    }
}