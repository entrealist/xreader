package com.example.xreader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),OnItemClickListener,ValueEventListener, NavigationView.OnNavigationItemSelectedListener {


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

//       doAsync{
//           SheetsService.getValues()
//       }

        booksRef.addValueEventListener(this)

        btnNav.setOnClickListener(View.OnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        })

        nv.setNavigationItemSelectedListener(this)
    }

    override fun onItemClicked(book: Book, position: Int) {

        Log.d("book",book.toString())
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE,book.id)

        startActivity(intent)
    }

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        var books = ArrayList<Book>()
        var chapters = ArrayList<Chapter>();


        p0.children.forEach {
//           val bookser = it.getValue(Book::class.java)
            //Log.d("bookser",bookser.toString())

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

        itemAdapter.setData(books)
        mainItemAdapter.setData(books,this)
        Log.d("data",books.toString())

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.policy -> {
                val intent = Intent(this, privacyActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, aboutActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(Gravity.LEFT)
        return true
    }
}
