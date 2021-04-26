package ru.startandroid.develop.lessonsqlite

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_activity.*
import ru.startandroid.develop.lessonsqlite.db.MyDbManager
import ru.startandroid.develop.lessonsqlite.db.MyIntentConstants

class EditActivity : AppCompatActivity() {
    val imageRequestCode = 10
    var tempImageUri = "empty"
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        getMyIntents()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == imageRequestCode){
            imMainImage.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    fun onClickAddImage(view: View) {

        in_layout.visibility = View.VISIBLE
        fbAddImage.visibility = View.GONE
    }

    fun onClickDelete(view: View) {

        in_layout.visibility = View.GONE
        fbAddImage.visibility = View.VISIBLE
    }

    fun onClickChooseImage(view: View) {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)
    }

    fun onClickSave(view: View) {

        val myTitle = edTitle.text.toString()
        val myDesc = edDesc.text.toString()

        if(myTitle != "" && myDesc != "")
        {
            myDbManager.insertToDB(myTitle, myDesc, tempImageUri)
            finish()
        }


    }

    override fun onResume() {

        super.onResume()
        myDbManager.openDB()
    }

    override fun onDestroy() {

        super.onDestroy()
        myDbManager.closeDB()
    }

    fun getMyIntents(){

        val i = intent

        if(i != null){

            if(i.getStringExtra(MyIntentConstants.I_TITLE_KEY) != null){
                fbAddImage.visibility = View.GONE
                edTitle.setText(i.getStringExtra(MyIntentConstants.I_TITLE_KEY))
                edDesc.setText(i.getStringExtra(MyIntentConstants.I_DESC_KEY))
                if(i.getStringExtra(MyIntentConstants.I_URI_KEY) != "empty"){

                    in_layout.visibility = View.VISIBLE
                    imMainImage.setImageURI(Uri.parse(i.getStringExtra(MyIntentConstants.I_URI_KEY)))
                    btnDelete.visibility = View.GONE
                    btnChooseImage.visibility = View.GONE
                }
            }
        }
    }
}