package com.example.xreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class ChapterActivity : AppCompatActivity(),OnItemClickListener {

    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        val sts:Array<String> = arrayOf("asdf","fasdf")
        rcItem.layoutManager = GridLayoutManager(this,2)
        rcItem.adapter = ChapterAdapter(sts,this)
    }

    override fun onItemClicked(title: String, position: Int) {

        val message = title
        val intent = Intent(this, DetailViewActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
