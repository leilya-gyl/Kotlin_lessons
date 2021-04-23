package ru.startandroid.develop.alertdialogexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMultiDialog()
    }

    private fun createSimpleDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert Dialog")
        builder.setMessage("this is alert dialog")
        builder.setNeutralButton("Info") { dialogInterface, which ->

        }
        builder.setNegativeButton("Cancel") { dialog, which ->

        }
        builder.setPositiveButton("Ok") { dialog, which ->

        }
        builder.show()
    }
    private fun createMultiDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("What you like")
        builder.setMultiChoiceItems(R.array.multi_item, null){dialog, which, choice ->
            Log.d("MyLog", "My choice is:" + which + " / Is: " + choice)
        }
        builder.setNeutralButton("Info") { dialogInterface, which ->

        }
        builder.setNegativeButton("Cancel") { dialog, which ->

        }
        builder.setPositiveButton("Ok") { dialog, which ->

        }
        builder.show()
    }
}