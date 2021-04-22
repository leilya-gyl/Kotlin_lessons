package ru.startandroid.develop.myintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.content_layout.*

class ContentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)

        myTitle.text = intent.getStringExtra("title")
        myContent.text = intent.getStringExtra("content")
        im.setImageResource(intent.getIntExtra("image", R.drawable.som))
    }
}