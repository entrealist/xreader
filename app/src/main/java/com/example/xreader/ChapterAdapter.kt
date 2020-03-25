package com.example.xreader

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xreader.model.Book
import com.example.xreader.model.Chapter
import kotlinx.android.synthetic.main.chapter_view_holder.view.*
import kotlinx.android.synthetic.main.item_view_holder.view.*

class ChapterAdapter (private var myDataset:Book,val onChapterClickListener:OnChapterClickListener) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {
    var selectedIndex:Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterAdapter.ChapterViewHolder{
       val itemViewHolderLayout = LayoutInflater.from(parent.context).inflate(R.layout.chapter_view_holder,parent,false)
        val itemViewHolder = ChapterViewHolder(itemViewHolderLayout)
        return itemViewHolder
    }

    override fun getItemCount(): Int {
       return myDataset.chapters?.size ?: 0
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(myDataset.chapters!!.get(position),position,selectedIndex,onChapterClickListener )
    }

    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(chapter: Chapter?, position: Int, selectedIndex:Int, onChapterClickListener:OnChapterClickListener){
          var textView =  itemView.findViewById<TextView>(R.id.title)

            textView.text = chapter?.title

            if (selectedIndex == position){
                Log.d("color","Color primery")
                itemView.container.setBackgroundResource(R.color.selected_item)
            }else{
                Log.d("color","Color accent")
                itemView.container.setBackgroundResource(R.color.white)
            }
            itemView.setOnClickListener {
                onChapterClickListener.onChapterClicked(chapter!!,position)
            }
        }
    }

}

interface OnChapterClickListener{
    fun onChapterClicked(chapter: Chapter, position: Int)
}
