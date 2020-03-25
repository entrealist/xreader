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
<<<<<<< HEAD
import androidx.recyclerview.widget.OrientationHelper

class MainActivity : AppCompatActivity(),OnItemClickListener {
=======
import android.util.Log
import com.example.xreader.model.Book
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(),OnItemClickListener,ValueEventListener {


>>>>>>> 31eea7a422041c79624531620d8f7f84963c6a3b
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    val database = Firebase.database
    var booksRef = database.getReference("books")
    var itemAdapter = ItemAdapter(this)



    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



<<<<<<< HEAD
        val sts:Array<String> = arrayOf("asdf","fasdf")
        rcItem.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL,false)
        rcItem.adapter = ItemAdapter(sts,this)
=======

        rcItem.layoutManager = LinearLayoutManager(this)
        rcItem.adapter = itemAdapter
>>>>>>> 31eea7a422041c79624531620d8f7f84963c6a3b

       doAsync{
           SheetsService.getValues()
       }

        booksRef.addValueEventListener(this)


    }

    override fun onItemClicked(book: Book, position: Int) {


        val intent = Intent(this, ChapterActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, book)
        }
        startActivity(intent)
    }

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        var books = ArrayList<Book>()
        Log.d("firebase",p0.toString())
        p0.children.forEach {
            val book = it.getValue(Book::class.java)
            book?.let { it1 ->

                books.add(it1) }
        }

        itemAdapter.setData(books)
        Log.d("data",books.toString())

    }
}
