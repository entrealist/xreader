package com.example.xreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.activity_detail_view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailViewActivity : AppCompatActivity(),OnTapClickListener,DetailFragment.OnFragmentInteractionListener{


    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    var CHAPTER_MESSAGE = "CHAPTER_MESSAGE"

    lateinit var tapBarAdapter:TapBarAdapter
    lateinit var pagerAdapter:FragmentPagerAdapter
    lateinit var book: Book
    lateinit var selectedChapter:Chapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        book = intent.extras.get(EXTRA_MESSAGE) as Book
        selectedChapter = intent.extras.get(CHAPTER_MESSAGE) as Chapter
        Log.d("chapter from chapter ",selectedChapter.toString())
        Log.d("Bokk from chapter",book.toString())

        txtBookTitle.text = book.title
        btnBack.setOnClickListener {
                    onBackPressed()
                }

        pagerAdapter  = object :FragmentPagerAdapter(this.supportFragmentManager){
            override fun getItem(position: Int): Fragment {
                return DetailFragment.newInstance(book.chapters!![position].content ?: "")
            }

            override fun getCount(): Int {
                return book.chapters?.size ?: 0
            }



        }
        vPager.setAdapter(pagerAdapter)
        vPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                tapBarAdapter.selectedIndex = position
                tapBarAdapter.notifyDataSetChanged();
                chepterRecyclerView.scrollToPosition(position)

            }

        })

        val layoutManager = GridLayoutManager(this,1)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL
        chepterRecyclerView.layoutManager = layoutManager
        tapBarAdapter = TapBarAdapter(book!!.chapters!!,this)
        chepterRecyclerView.adapter = tapBarAdapter




    }
    override fun onTapClickListener(title: String, position: Int) {
        tapBarAdapter.selectedIndex = position
        tapBarAdapter.notifyDataSetChanged()
        vPager.setCurrentItem(position)

    }


    override fun onFragmentInteraction(uri: Uri) {

    }

}
