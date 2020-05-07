package com.example.xreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.activity_chapter.btnBack
import kotlinx.android.synthetic.main.activity_chapter.txtBookTitle
import kotlinx.android.synthetic.main.activity_detail_view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class ChapterActivity : AppCompatActivity(),OnChapterClickListener,ValueEventListener {

    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    var CHAPTER_MESSAGE = "CHAPTER_MESSAGE"
    lateinit var book:Book
    val database = Firebase.database
    var booksRef = database.getReference("books")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

         val bookID = intent.extras.get(EXTRA_MESSAGE) as String
        booksRef.child(bookID).addValueEventListener(this)


    }
    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        var chapters = ArrayList<Chapter>();
        p0.child("chapters").children.forEach{

            Log.d("serialie content",p0.child("content").toString())
            val id = (it.child("id").value).toString()
            val content = (it.child("content").value).toString()
            val title = (it.child("title").value).toString()

            val chapter = Chapter(id,content,title)

            chapter?.let { it1 ->

                chapters.add(it1) }
        }

        val id = (p0.child("id").value).toString()
        val description = (p0.child("description").value).toString()
        val image = (p0.child("image").value).toString()
        val title = (p0.child("title").value).toString()

        book = Book(id,chapters,title,description,image)
        txtBookTitle.text = book.title
        btnBack.setOnClickListener {
            onBackPressed()
        }
        items.layoutManager = GridLayoutManager(this,2)
        items.adapter = ChapterAdapter(book,this)

    }

    override fun onChapterClicked(chapter: Chapter, position: Int) {
        Log.d("chapter selected",chapter.toString())

        val intent = Intent(this, DetailViewActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,book)
            putExtra(CHAPTER_MESSAGE,chapter)
        }
        startActivity(intent)
    }
}
