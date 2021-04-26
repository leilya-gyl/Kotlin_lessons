package ru.startandroid.develop.lessonsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.startandroid.develop.lessonsqlite.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {

        super.onResume()
        myDbManager.openDB()
    }
    fun onClickNew(view: View) {

        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {

        super.onDestroy()
        myDbManager.closeDB()
    }
}
