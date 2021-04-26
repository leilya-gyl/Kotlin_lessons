package ru.startandroid.develop.lessonsqlite

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import ru.startandroid.develop.lessonsqlite.db.MyAdapter
import ru.startandroid.develop.lessonsqlite.db.MyDbManager

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initSearchView()

    }

    override fun onResume() {

        super.onResume()
        myDbManager.openDB()
        fillAdapter()
    }

    fun onClickNew(view: View) {

        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {

        super.onDestroy()
        myDbManager.closeDB()
    }

    fun init(){

        rcView.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcView)
        rcView.adapter = myAdapter
    }

    private fun initSearchView(){
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = myDbManager.readDBData(newText!!)
                myAdapter.updateAdapter(list)
                return true
            }
        })
    }

    private fun fillAdapter(){

        val list = myDbManager.readDBData("")
        myAdapter.updateAdapter(list)
        if(list.size > 0)
            tvNoElements.visibility = View.GONE
        else
            tvNoElements.visibility = View.VISIBLE
    }

    private fun getSwapMg(): ItemTouchHelper{
        return ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }
}
