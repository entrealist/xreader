package com.example.xreader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.xreader.model.Book

class MainItemAdapter(val itemClickListener: OnItemClickListener) : PagerAdapter()
{
    var myDataset = ArrayList<Book>()
    lateinit var layoutInflater : LayoutInflater
    lateinit var context:Context

    fun setData(books:ArrayList<Book>, c:Context){
        this.myDataset = books
        this.context = c
        this.notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return myDataset.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.item_view_holder,container,false)

        var textView =  view.findViewById<TextView>(R.id.title)
        var desc =  view.findViewById<TextView>(R.id.desc)
        textView.text = myDataset.get(position).title
//        desc.text = myDataset.get(position).description

        view.setOnClickListener {
            itemClickListener.onItemClicked(myDataset.get(position),position)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView((`object` as View?))
    }
}