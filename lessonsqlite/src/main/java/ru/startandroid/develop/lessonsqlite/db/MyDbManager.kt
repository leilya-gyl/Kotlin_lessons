package ru.startandroid.develop.lessonsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(val context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db:SQLiteDatabase? = null

    fun openDB(){
        db = myDbHelper.writableDatabase
    }

    fun insertToDB(title:String, content:String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title) // (key, value)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, title)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun readDBData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)
        /*with(cursor){
            while(this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        }*/
        while(cursor?.moveToNext()!!){
            val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }

    fun closeDB(){
        myDbHelper.close()
    }
}