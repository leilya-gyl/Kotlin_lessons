package ru.startandroid.develop.lessonsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.startandroid.develop.lessonsqlite.db.MyDbManager

class MainActivity : AppCompatActivity() {
    private val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDB()
        val dataList = myDbManager.readDBData()
        for (item in dataList) {
            textView.append(item)
            textView.append("\n")
        }
    }

    fun onClickSave(view: View) {
        textView.text = ""
        myDbManager.insertToDB(editName.text.toString(), editContent.text.toString())
        val dataList = myDbManager.readDBData()
        for (item in dataList) {
            textView.append(item)
            textView.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDB()
    }
}