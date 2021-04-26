package ru.startandroid.develop.lessonsqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDbManager(val context: Context) {

    val myDbHelper = MyDbHelper(context)
    var db:SQLiteDatabase? = null

    fun openDB(){

        db = myDbHelper.writableDatabase
    }

    fun insertToDB(title:String, content:String, uri:String){

        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title) // (key, value)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URI, uri)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun removeItemFromDB(id:String){

        val selection = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selection, null)
    }

    fun readDBData(searchText: String): ArrayList<ListItem>{

        val dataList = ArrayList<ListItem>()
        val selection = "${MyDbNameClass.COLUMN_NAME_TITLE} like ?"
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, selection, arrayOf("%$searchText%"), null, null, null)
        /*with(cursor){
            while(this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        }*/
        while(cursor?.moveToNext()!!){
            val dataTitle = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            val dataContent = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CONTENT))
            val dataUri = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_IMAGE_URI))
            val dataID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val item = ListItem()
            item.title = dataTitle
            item.desc = dataContent
            item.uri = dataUri
            item.id = dataID
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }

    fun closeDB(){
        myDbHelper.close()
    }
}