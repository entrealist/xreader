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
import android.util.Log
import androidx.recyclerview.widget.OrientationHelper
import androidx.viewpager.widget.ViewPager
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import com.google.android.gms.common.util.ArrayUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(),OnItemClickListener,ValueEventListener {


    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    val database = Firebase.database
    var booksRef = database.getReference("books")
    var itemAdapter = ItemAdapter(this)

    var mainItemAdapter = MainItemAdapter(this)

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        rcItem.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
//        rcItem.adapter = itemAdapter

        ////////// mine ////////////////////
        viewPager.adapter = mainItemAdapter
        viewPager.setPadding(130,0,130,0);

       doAsync{
           SheetsService.getValues()
       }

        booksRef.addValueEventListener(this)
    }

    override fun onItemClicked(book: Book, position: Int) {
        Log.d("book",book.toString())
        val intent = Intent(this, ChapterActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, book)
        }

        startActivity(intent)
    }

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        var books = ArrayList<Book>()
        var chapters = ArrayList<Chapter>();

        Log.d("firebase",p0.toString())

        p0.children.forEach {
//            val book = it.getValue(Book::class.java)

            it.child("chapters").children.forEach{

                val id = (it.child("id").value).toString()
                val content = (it.child("content").value).toString()
                val title = (it.child("title").value).toString()

                val chapter = Chapter(id,content,title)

                chapter?.let { it1 ->

                    chapters.add(it1) }
            }

            val id = (it.child("id").value).toString()
            val description = (it.child("description").value).toString()
            val image = (it.child("image").value).toString()
            val title = (it.child("title").value).toString()

            val book = Book(id,chapters,title,description,image)

            book?.let { it1 ->

                books.add(it1) }

//            Log.d("a", (it.child("id").value).toString())
        }

//        itemAdapter.setData(books)
        mainItemAdapter.setData(books,this)
        Log.d("data",books.toString())

    }
}
