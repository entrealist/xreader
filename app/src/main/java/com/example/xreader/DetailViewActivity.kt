package com.example.xreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail_view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class DetailViewActivity : AppCompatActivity(),OnItemClickListener,DetailFragment.OnFragmentInteractionListener{

    lateinit var tapBarAdapter:TapBarAdapter
    lateinit var pagerAdapter:FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        pagerAdapter  = object :FragmentPagerAdapter(this.supportFragmentManager){
            override fun getItem(position: Int): Fragment {
                return DetailFragment()
            }

            override fun getCount(): Int {
                return 100
            }

        }
        viewPager.setAdapter(pagerAdapter)
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
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



        val sts:Array<String> = arrayOf("asdf","fasdf")
        val layoutManager = GridLayoutManager(this,1)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL
        chepterRecyclerView.layoutManager = layoutManager
        tapBarAdapter = TapBarAdapter(sts,this)
        chepterRecyclerView.adapter = tapBarAdapter



    }

    override fun onItemClicked(title: String, position: Int) {
        tapBarAdapter.selectedIndex = position
        tapBarAdapter.notifyDataSetChanged()
        viewPager.setCurrentItem(position)

    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}
