package com.example.xreader

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.os.StrictMode
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.OrientationHelper

class MainActivity : AppCompatActivity(),OnItemClickListener {
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val sts:Array<String> = arrayOf("asdf","fasdf")
        rcItem.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
        rcItem.adapter = ItemAdapter(sts,this)

       doAsync{
           SheetsService.getValues()
       }

    }

    override fun onItemClicked(title: String, position: Int) {

        val message = title
        val intent = Intent(this, ChapterActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
