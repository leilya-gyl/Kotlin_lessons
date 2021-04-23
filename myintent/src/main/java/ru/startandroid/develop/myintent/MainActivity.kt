package ru.startandroid.develop.myintent

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var adapter:MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener( this )

        var list = ArrayList<ListItem>()
        list.addAll(fillArrays(resources.getStringArray(R.array.fish)
                ,resources.getStringArray(R.array.fish_content),
                getImageID(R.array.fish_image)))
       
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter =  MyAdapter(list, this)
        rcView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.id_fish -> {
                                Toast.makeText(this, "Id fish", Toast.LENGTH_SHORT).show()
                                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.fish),
                                        resources.getStringArray(R.array.fish_content), getImageID(R.array.fish_image)))
                            }
            R.id.id_nazh -> {
                                Toast.makeText(this, "Id nazhivka", Toast.LENGTH_SHORT).show()
                                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.nazh),
                                        resources.getStringArray(R.array.nazh_content), getImageID(R.array.nazh_image)))
                            }
            R.id.id_sna -> Toast.makeText(this, "Id snasti", Toast.LENGTH_SHORT).show()
            R.id.id_his -> Toast.makeText(this, "Id story", Toast.LENGTH_SHORT).show()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun fillArrays(titleArray:Array<String>, contentArray:Array<String>, imageArray:IntArray):List<ListItem>
    {
        var listItemArray = ArrayList<ListItem>()
        for(n in 0..titleArray.size -1)
        {
            var listItem = ListItem(imageArray[n], titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageID(imageArrayID:Int):IntArray
    {
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayID)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }
}