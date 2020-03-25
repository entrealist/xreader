package com.example.xreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xreader.model.Book
import kotlinx.android.synthetic.main.item_view_holder.view.*

class ItemAdapter (val onItemClickListener:OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var myDataset = ArrayList<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val itemViewHolderLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_view_holder,parent,false)
        val itemViewHolder = ItemViewHolder(itemViewHolderLayout)
        return itemViewHolder
    }
    fun setData(books:ArrayList<Book>){
        this.myDataset = books
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return myDataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(myDataset[position],position,onItemClickListener )
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(book:Book,position: Int,onItemClickListener:OnItemClickListener){
          var textView =  itemView.findViewById<TextView>(R.id.title)
            textView.text = book.title
            itemView.setOnClickListener {
                onItemClickListener.onItemClicked(book,position)
            }
        }
    }

}

interface OnItemClickListener{
    fun onItemClicked(book: Book,position: Int)
}
