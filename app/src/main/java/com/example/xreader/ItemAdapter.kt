package com.example.xreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_holder.view.*

class ItemAdapter (private val myDataset: Array<String>, val onItemClickListener:OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val itemViewHolderLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_view_holder,parent,false)
        val itemViewHolder = ItemViewHolder(itemViewHolderLayout)
        return itemViewHolder
    }

    override fun getItemCount(): Int {
       return 10
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind("Title"+position,position,onItemClickListener )
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(title:String,position: Int,onItemClickListener:OnItemClickListener){
          var textView =  itemView.findViewById<TextView>(R.id.title)
            textView.text = "TITITLE"
            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(title,position)
            }
        }
    }

}

interface OnItemClickListener{
    fun onItemClicked(title: String,position: Int)
}
