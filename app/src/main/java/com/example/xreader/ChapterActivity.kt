package com.example.xreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.activity_chapter.btnBack
import kotlinx.android.synthetic.main.activity_chapter.txtBookTitle
import kotlinx.android.synthetic.main.activity_detail_view.*
import kotlinx.android.synthetic.main.activity_main.*

class ChapterActivity : AppCompatActivity(),OnChapterClickListener {

    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    var CHAPTER_MESSAGE = "CHAPTER_MESSAGE"
    lateinit var book:Book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        book = intent.extras.get(EXTRA_MESSAGE) as Book

        txtBookTitle.text = book.title
        btnBack.setOnClickListener {
            onBackPressed()
        }
        items.layoutManager = GridLayoutManager(this,2)
        items.adapter = ChapterAdapter(book,this)
    }

    override fun onChapterClicked(chapter: Chapter, position: Int) {


        val intent = Intent(this, DetailViewActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,book )
            putExtra(CHAPTER_MESSAGE,chapter)
        }
        startActivity(intent)
    }
}
